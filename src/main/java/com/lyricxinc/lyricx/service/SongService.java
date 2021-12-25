package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.LyricxBaseException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.core.exception.RollbackException;
import com.lyricxinc.lyricx.model.*;
import com.lyricxinc.lyricx.model.validator.group.OnSongCreate;
import com.lyricxinc.lyricx.model.validator.group.OnSongUpdate;
import com.lyricxinc.lyricx.repository.SongRepository;
import com.lyricxinc.lyricx.service.amazon.AmazonClientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;
import static com.lyricxinc.lyricx.core.util.StringValidatorUtil.getUuidOrNull;
import static com.lyricxinc.lyricx.core.util.StringValidatorUtil.isStringNotEmpty;

/**
 * The type Song service.
 */
@Log4j2
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
    private Song getSongById(long id) {

        return songRepository.findById(id).orElseThrow(() -> new NotFoundException(LYRICX_ERR_10));
    }

    /**
     * Gets song by surrogate key.
     *
     * @param surrogateKey the surrogate key
     * @return the song by surrogate key
     */
    public Song getSongBySurrogateKey(String surrogateKey) {

        return songRepository.findBySurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(LYRICX_ERR_10));
    }

    /**
     * Gets songs added by contributor.
     *
     * @param request    the request
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the songs added by contributor
     */
    public Page<Song> getSongsAddedByContributor(HttpServletRequest request, int pageNumber, int pageSize) {
        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
        return songRepository.findByAddedBy(contributor, PageRequest.of(pageNumber, pageSize, Sort.by("name"))).orElseThrow(() -> new NotFoundException(LYRICX_ERR_10));
    }

    /**
     * Save song song.
     *
     * @param request                the request
     * @param payload                the payload
     * @param artistSurrogateKeyList the artist surrogate key list
     * @param genreIdList            the genre id list
     * @param image                  the image
     * @return the song
     */
    public Song saveSong(HttpServletRequest request, final @Valid Song payload, List<String> artistSurrogateKeyList, List<Short> genreIdList, MultipartFile image) {

        Song song;

        try {
            if(payload.getSurrogateKey() == null || payload.getSurrogateKey().isEmpty()){
                song = createSong(request, payload, artistSurrogateKeyList, genreIdList, image);
            }else {
                log.warn("Song already exists. Discarding creating a new one");
                throw new ForbiddenException(LYRICX_ERR_34);
            }
        } catch (ForbiddenException ex) {
             if (LYRICX_ERR_34.name().equals(ex.getCode()) && payload.getSurrogateKey() != null && !payload.getSurrogateKey().isEmpty()) {
                log.warn("Song already exists. Proceeding with updating", ex);
                song = updateSong(request, payload, artistSurrogateKeyList, genreIdList, image);
            } else {
                throw ex;
            }
        }

        return songRepository.findById(song.getId()).orElse(null);
    }

    /**
     * Create song song.
     *
     * @param request                the request
     * @param payload                the payload
     * @param artistSurrogateKeyList the artist surrogate key list
     * @param genreIdList            the genre id list
     * @param image                  the image
     * @return the song
     */
    @Transactional(rollbackFor = RollbackException.class)
    @Validated(OnSongCreate.class)
    public Song createSong(HttpServletRequest request, final @Valid Song payload, final List<String> artistSurrogateKeyList, final List<Short> genreIdList, final MultipartFile image) {

        Album album = albumService.getAlbumBySurrogateKey(payload.getAlbum().getSurrogateKey());

        validateSongExists(payload, album);

        payload.setAddedBy(contributorService.getContributorByHttpServletRequest(request));
        payload.setLastModifiedBy(contributorService.getContributorByHttpServletRequest(request));
        payload.setPublishedState(false);
        payload.setPublishedBy(null);
        payload.setPublishedDate(null);

        payload.setAlbum(album);

        Language language = languageService.getLanguageByLanguageCode(payload.getLanguage().getLanguageCode());
        payload.setLanguage(language);

        if (image != null) {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFoldersType.SONG_FOLDER);
            payload.setImgUrl(imgUrl);
        }

        Song song = songRepository.save(payload);

        try{
            updateSongArtistList(song, artistSurrogateKeyList, false);
        } catch (Exception ex) {
            if (ex instanceof LyricxBaseException && ((LyricxBaseException)ex).getCode().equals(LYRICX_ERR_29.name())) {
                // not an issue when song doesn't have any artists other than main artist
                log.warn("Didn't update song artist list", ex);
            } else {
                log.error("Error while saving the song. Rolling back changes", ex);
                throw new RollbackException(LYRICX_ERR_37);
            }
        }

        try{
            updateSongGenreList(song, genreIdList, false);
        } catch (Exception ex) {
            log.error("Didn't update song genre list. Error while saving the song. Rolling back changes", ex);
            throw new RollbackException(LYRICX_ERR_39);
        }

        return songRepository.findById(song.getId()).orElse(null);
    }

    /**
     * Update song song.
     *
     * @param request                the request
     * @param payload                the payload
     * @param artistSurrogateKeyList the artist surrogate key list
     * @param genreIdList            the genre id list
     * @param image                  the image
     * @return the song
     */
    @Validated(OnSongUpdate.class)
    public Song updateSong(final HttpServletRequest request, final @Valid Song payload, final List<String> artistSurrogateKeyList, final List<Short> genreIdList, final MultipartFile image) {

        Song modifiedSong = updateSongDetails(request, payload, image, contributorService::checkNonSeniorContributorEditsVerifiedContent);

        Song song = songRepository.save(modifiedSong);

        try {
            updateSongArtistList(song, artistSurrogateKeyList, true);

            updateSongGenreList(song, genreIdList, true);
        } catch (LyricxBaseException ex) {
            if (ex.getCode().equals(LYRICX_ERR_29.name())) {
                log.warn("Didn't update song artist list", ex);
            }
            if (ex.getCode().equals(LYRICX_ERR_30.name())) {
                log.warn("Didn't update song generic list", ex);
            }
        }

        return songRepository.findById(song.getId()).orElse(null);
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
        this.amazonClientService.deleteFileFromS3Bucket(song.getImgUrl(), AmazonClientService.S3BucketFoldersType.SONG_FOLDER);

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

    private void removeSong(Song song) {
        songRepository.delete(song);
    }

    private String getSongImgUrl(String surrogateKey) {

        return songRepository.findImgUrlUsingSurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(LYRICX_ERR_24));
    }

    private String getSongImgUrl(Long id) {

        return songRepository.findImgUrlUsingId(id).orElseThrow(() -> new NotFoundException(LYRICX_ERR_24));
    }

    private Song updateSongDetails(final HttpServletRequest request, final Song payload, @Nullable final MultipartFile image, BiConsumer<Contributor, Song> contributorSongBiConsumer) {

        Optional.ofNullable(getUuidOrNull(payload.getSurrogateKey())).orElseThrow(
                () -> new ForbiddenException(LYRICX_ERR_38));

        Song existingSong = getSongBySurrogateKey(payload.getSurrogateKey());

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        contributorSongBiConsumer.accept(contributor, existingSong);

        if(payload.getPublishedState() != null) {
            existingSong.setPublishedState(payload.getPublishedState());
            existingSong.setPublishedBy(contributor);
            existingSong.setPublishedDate(LocalDateTime.now());
        }

        if (isStringNotEmpty(payload.getName())) {
            existingSong.setName(payload.getName());
        }

        if (payload.getAlbum() != null && getUuidOrNull(payload.getAlbum().getSurrogateKey()) != null
                && !payload.getAlbum().getSurrogateKey().equals(existingSong.getAlbum().getSurrogateKey())) {
            Album newAlbum = albumService.getAlbumBySurrogateKey(payload.getAlbum().getSurrogateKey());
            existingSong.setAlbum(newAlbum);
        }

        if(payload.getGuitarKey() != null) {
            existingSong.setGuitarKey(payload.getGuitarKey().trim());
        }

        if(payload.getBeat() != null) {
            existingSong.setBeat(payload.getBeat().trim());
        }

        if (payload.getLanguage() != null && payload.getLanguage().getLanguageCode() != null && !payload.getLanguage().getLanguageCode().equals(
                existingSong.getLanguage().getLanguageCode())) {
            Language newLanguage = languageService.getLanguageByLanguageCode(payload.getLanguage().getLanguageCode());
            existingSong.setLanguage(newLanguage);
        }

        if (isStringNotEmpty(payload.getKeywords())) {
            existingSong.setKeywords(payload.getKeywords());
        }

        if (payload.getLyrics() != null && payload.getLyrics().length > 0) {
            existingSong.setLyrics(payload.getLyrics());
        }

        if (isStringNotEmpty(payload.getYouTubeLink())) {
            existingSong.setYouTubeLink(payload.getYouTubeLink().trim());
        }

        if (payload.getSpotifyLink() != null) {
            existingSong.setSpotifyLink(payload.getSpotifyLink().trim());
        }

        if (payload.getDeezerLink() != null) {
            existingSong.setDeezerLink(payload.getDeezerLink().trim());
        }

        if (payload.getAppleMusicLink() != null) {
            existingSong.setAppleMusicLink(payload.getAppleMusicLink().trim());
        }

        if (Boolean.TRUE.equals(payload.getIsExplicit()) || Boolean.FALSE.equals(payload.getIsExplicit())) {
            existingSong.setIsExplicit(payload.getIsExplicit());
        }

        if (image != null) {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFoldersType.SONG_FOLDER);

            String oldImgUrl = existingSong.getImgUrl();

            //delete old song image from S3 bucket
            if (oldImgUrl != null) {
                this.amazonClientService.deleteFileFromS3Bucket(oldImgUrl, AmazonClientService.S3BucketFoldersType.SONG_FOLDER);
            }

            existingSong.setImgUrl(imgUrl);
        }

        existingSong.setLastModifiedBy(contributor);

        return existingSong;
    }

    /**
     * Update song artist list.
     *
     * @param song                   the song
     * @param artistSurrogateKeyList the artist surrogate key list
     * @param deleteExisting         the delete existing
     */
    public void updateSongArtistList(final Song song, final List<String> artistSurrogateKeyList, final boolean deleteExisting) {

        if (song == null || artistSurrogateKeyList == null) {
            throw new NotFoundException(LYRICX_ERR_29);
        }

        Set<String> artistSurrogateKeySet = new HashSet<>(artistSurrogateKeyList);

        artistSurrogateKeyList.removeIf(artistSurrogateKey -> artistSurrogateKey.equals(song.getAlbum().getArtist().getSurrogateKey()));

        if (artistSurrogateKeySet.isEmpty()) {
            throw new NotFoundException(LYRICX_ERR_29);
        }

        List<Artist> artistList = artistService.findArtistsBySurrogateKeys(artistSurrogateKeySet);

        if (artistList.isEmpty()) {
            throw new NotFoundException(LYRICX_ERR_29);
        }

        Set<ArtistSong> existingArtistSongSet = song.getArtistSongs();

        if (deleteExisting && existingArtistSongSet != null && !existingArtistSongSet.isEmpty()) {
            artistSongService.deleteArtistSongInBatch(existingArtistSongSet);
        }

        artistSongService.createArtistSongInBatch(song, artistList);
    }

    /**
     * Update song genre list.
     *
     * @param song           the song
     * @param genreIdList    the genre id list
     * @param deleteExisting the delete existing
     */
    public void updateSongGenreList(final Song song, final List<Short> genreIdList, final boolean deleteExisting) {

        if (song == null || genreIdList == null) {
            throw new NotFoundException(LYRICX_ERR_30);
        }

        Set<Short> genreIdSet = new HashSet<>(genreIdList);

        if (genreIdSet.isEmpty()) {
            throw new NotFoundException(LYRICX_ERR_30);
        }

        List<Genre> genreList = genreService.findGenreByIds(genreIdSet);

        if (genreList.isEmpty()) {
            throw new NotFoundException(LYRICX_ERR_30);
        }

        Set<SongGenre> existingSongGenreSet = song.getSongGenres();

        if (deleteExisting && existingSongGenreSet != null && !existingSongGenreSet.isEmpty()) {
            songGenreService.deleteSongGenreInBatch(existingSongGenreSet);
        }

        songGenreService.createSongGenre(song, genreList);
    }

    /**
     * Remove artist list.
     */
    public void removeArtistList(){

    }

    private void validateSongExists(final Song payload, final Album album) {
        String songName = payload.getName();

        if (songName != null && !songName.isEmpty()) {

            Set<Song> setSong = album.getSongs();

            setSong.forEach(song -> {
                if (song.getName().equals(songName)) {
                    throw new ForbiddenException(LYRICX_ERR_34);
                }
            });
        }
    }

}
