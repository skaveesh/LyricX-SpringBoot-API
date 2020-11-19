package com.lyricxinc.lyricx.service;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;
import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.LyricxBaseException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.model.*;
import com.lyricxinc.lyricx.model.validator.group.OnSongCreate;
import com.lyricxinc.lyricx.model.validator.group.OnSongUpdate;
import com.lyricxinc.lyricx.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * The type Song service.
 */
@Validated
@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumService albumService;
    private final LanguageService languageService;
    private final ContributorService contributorService;
    private final ArtistService artistService;
    private final ArtistSongService artistSongService;
    private final GenreService genreService;
    private final SongGenreService songGenreService;
    private final AmazonClientService amazonClientService;

    /**
     * The Song image url.
     */
    @Value("${com.lyricxinc.lyricx.songImageUrl}")
    String songImageUrl;

    /**
     * Instantiates a new Song service.
     *
     * @param songRepository      the song repository
     * @param albumService        the album service
     * @param languageService     the language service
     * @param contributorService  the contributor service
     * @param artistService       the artist service
     * @param artistSongService   the artist song service
     * @param genreService        the genre service
     * @param songGenreService    the song genre service
     * @param amazonClientService the amazon client service
     */
    @Autowired
    public SongService(SongRepository songRepository, AlbumService albumService, LanguageService languageService, ContributorService contributorService, ArtistService artistService, ArtistSongService artistSongService, GenreService genreService, SongGenreService songGenreService, AmazonClientService amazonClientService) {

        this.songRepository = songRepository;
        this.albumService = albumService;
        this.languageService = languageService;
        this.contributorService = contributorService;
        this.artistService = artistService;
        this.artistSongService = artistSongService;
        this.genreService = genreService;
        this.songGenreService = songGenreService;
        this.amazonClientService = amazonClientService;
    }

    /**
     * Gets song by id.
     *
     * @param id the id
     * @return the song by id
     */
    public Song getSongById(long id) {

        return songRepository.findById(id).orElseThrow(() -> new ForbiddenException(LYRICX_ERR_10.getErrorMessage(), LYRICX_ERR_10.name()));
    }

    /**
     * Gets song by surrogate key.
     *
     * @param surrogateKey the surrogate key
     * @return the song by surrogate key
     */
    public Song getSongBySurrogateKey(String surrogateKey) {

        return songRepository.findBySurrogateKey(surrogateKey).orElseThrow(() -> new ForbiddenException(LYRICX_ERR_10.getErrorMessage(), LYRICX_ERR_10.name()));
    }

    /**
     * Create song.
     *
     * @param request                the request
     * @param payload                the payload
     * @param artistSurrogateKeyList the artist surrogate key list
     * @param genreIdList            the genre id list
     */
    @Validated(OnSongCreate.class)
    public void createSong(HttpServletRequest request, final @Valid Song payload, List<String> artistSurrogateKeyList, List<Short> genreIdList) {

        payload.setAddedBy(contributorService.getContributorByHttpServletRequest(request));
        payload.setLastModifiedBy(contributorService.getContributorByHttpServletRequest(request));
        payload.setPublishedState(false);
        payload.setPublishedBy(null);
        payload.setPublishedDate(null);

        Album album = albumService.getAlbumBySurrogateKey(payload.getAlbum().getSurrogateKey());
        payload.setAlbum(album);

        Language language = languageService.getLanguageByLanguageCode(payload.getLanguage().getLanguageCode());
        payload.setLanguage(language);

        Song song = songRepository.save(payload);

        updateSongArtistList(song, artistSurrogateKeyList);
        updateSongGenreList(song, genreIdList);
    }

    /**
     * Update song.
     *
     * @param request                the request
     * @param payload                the payload
     * @param artistSurrogateKeyList the artist surrogate key list
     * @param genreIdList            the genre id list
     * @param image                  the image
     */
    @Validated(OnSongUpdate.class)
    public void updateSong(final HttpServletRequest request, final @Valid Song payload, List<String> artistSurrogateKeyList, List<Short> genreIdList, MultipartFile image) {

        updateSongDetails(request, payload, image, contributorService::checkNonSeniorContributorEditsVerifiedContent);

        Song song = songRepository.save(payload);

        try
        {
            updateSongArtistList(song, artistSurrogateKeyList);

            updateSongGenreList(song, genreIdList);
        }catch (LyricxBaseException ex){
            //todo log here - didn't update these list - can found which threw error based on error id
        }
    }

    /**
     * Remove album art.
     *
     * @param request the request
     * @param songId  the song id
     */
    public void removeAlbumArt(HttpServletRequest request, long songId) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Song song = this.getSongById(songId);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, song);

        //delete old song image from S3 bucket
        this.amazonClientService.deleteFileFromS3Bucket(song.getImgUrl(), AmazonClientService.S3BucketFolders.SONG_FOLDER);

        song.setImgUrl(null);

        songRepository.save(song);
    }

    /**
     * Remove song.
     *
     * @param request the request
     * @param id      the id
     */
    public void removeSong(HttpServletRequest request, long id) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Song song = this.getSongById(id);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, song);

        songRepository.deleteById(id);
    }

    private String getSongImgUrl(String surrogateKey) {

        return songRepository.findImgUrlUsingSurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(LYRICX_ERR_24.getErrorMessage(), LYRICX_ERR_24.name()));
    }

    private String getSongImgUrl(Long id) {

        return songRepository.findImgUrlUsingId(id).orElseThrow(() -> new NotFoundException(LYRICX_ERR_24.getErrorMessage(), LYRICX_ERR_24.name()));
    }

    private void updateSongDetails(final HttpServletRequest request, final Song payload, @Nullable final MultipartFile image, BiConsumer<Contributor, Song> contributorSongBiConsumer) {

        Song oldSong = getSongBySurrogateKey(payload.getSurrogateKey());

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        contributorSongBiConsumer.accept(contributor, oldSong);

        payload.setLastModifiedBy(contributor);
        payload.setId(oldSong.getId());

        if (image != null)
        {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.SONG_FOLDER);

            String oldImgUrl = oldSong.getImgUrl();

            //delete old song image from S3 bucket
            if (oldImgUrl != null)
            {
                this.amazonClientService.deleteFileFromS3Bucket(oldImgUrl, AmazonClientService.S3BucketFolders.SONG_FOLDER);
            }

            payload.setImgUrl(imgUrl);
        }

        if (payload.getAlbum() == null || payload.getAlbum().getSurrogateKey() == null)
        {
            payload.setAlbum(oldSong.getAlbum());
        }
        else
        {
            Album newAlbum = albumService.getAlbumBySurrogateKey(payload.getAlbum().getSurrogateKey());

            if (image == null && oldSong.getImgUrl() != null)
            {
                payload.setImgUrl(newAlbum.getImgUrl());
            }
            payload.setAlbum(newAlbum);
        }

        if (payload.getLanguage() == null || payload.getLanguage().getLanguageCode() == null)
        {
            payload.setLanguage(oldSong.getLanguage());
        }
        else
        {
            Language newLanguage = languageService.getLanguageByLanguageCode(payload.getLanguage().getLanguageCode());
            payload.setLanguage(newLanguage);
        }

    }

    /**
     * Update song artist list.
     *
     * @param song                   the song
     * @param artistSurrogateKeyList the artist surrogate key list
     */
    public void updateSongArtistList(Song song, List<String> artistSurrogateKeyList) {

        if(song == null || artistSurrogateKeyList == null){
            throw new ForbiddenException(LYRICX_ERR_29.getErrorMessage(), LYRICX_ERR_29.name());
        }

        Set<String> artistSurrogateKeySet = new HashSet<>(artistSurrogateKeyList);

        List<Artist> artistList = artistService.findArtistsBySurrogateKeys(artistSurrogateKeySet);

        if(artistList.isEmpty()){
            throw new ForbiddenException(LYRICX_ERR_29.getErrorMessage(), LYRICX_ERR_29.name());
        }

        artistSongService.createArtistSong(song, artistList);
    }

    /**
     * Update song genre list.
     *
     * @param song        the song
     * @param genreIdList the genre id list
     */
    public void updateSongGenreList(Song song, List<Short> genreIdList){

        if(song == null || genreIdList == null){
            throw new ForbiddenException(LYRICX_ERR_30.getErrorMessage(), LYRICX_ERR_30.name());
        }

        Set<Short> genreIdSet = new HashSet<>(genreIdList);

        List<Genre> genreList = genreService.findGenreByIds(genreIdSet);

        if(genreList.isEmpty()){
            throw new ForbiddenException(LYRICX_ERR_30.getErrorMessage(), LYRICX_ERR_30.name());
        }

        songGenreService.createSongGenre(song, genreList);
    }

    /**
     * Remove artist list.
     */
    public void removeArtistList(){

    }

}
