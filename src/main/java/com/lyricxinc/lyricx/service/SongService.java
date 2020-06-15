package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.Language;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.model.validator.group.OnSongCreate;
import com.lyricxinc.lyricx.model.validator.group.OnSongUpdate;
import com.lyricxinc.lyricx.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.function.Consumer;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

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
     * @param amazonClientService the amazon client service
     * @param conversionService   the conversion service
     */
    @Autowired
    public SongService(SongRepository songRepository, AlbumService albumService, LanguageService languageService, ContributorService contributorService, AmazonClientService amazonClientService, ConversionService conversionService) {

        this.songRepository = songRepository;
        this.albumService = albumService;
        this.languageService = languageService;
        this.contributorService = contributorService;
        this.amazonClientService = amazonClientService;
    }

    /**
     * Gets song by id.
     *
     * @param id the id
     * @return the song by id
     */
    public Song getSongById(long id) {

        return songRepository.findById(id).orElseThrow(() -> new ForbiddenException(ErrorMessage.LYRICX_ERR_10, ErrorCode.LYRICX_ERR_10));
    }

    /**
     * Gets song by surrogate key.
     *
     * @param surrogateKey the surrogate key
     * @return the song by surrogate key
     */
    public Song getSongBySurrogateKey(String surrogateKey) {

        return songRepository.findBySurrogateKey(surrogateKey).orElseThrow(() -> new ForbiddenException(ErrorMessage.LYRICX_ERR_10, ErrorCode.LYRICX_ERR_10));
    }

    /**
     * Create song.
     *
     * @param request the request
     * @param payload the payload
     */
    @Validated(OnSongCreate.class)
    public void createSong(HttpServletRequest request, final  @Valid Song payload) {

//        if(payload == null){
//            throw new IllegalObjectException(ErrorMessage.LYRICX_ERR_28, ErrorMessage.LYRICX_ERR_28);
//        }

        payload.setAddedBy(contributorService.getContributorByHttpServletRequest(request));
        payload.setLastModifiedBy(contributorService.getContributorByHttpServletRequest(request));
        payload.setPublishedState(false);
        payload.setPublishedBy(null);
        payload.setPublishedDate(null);

        Album album = albumService.getAlbumBySurrogateKey(payload.getAlbum().getSurrogateKey());
        payload.setAlbum(album);

        Language language = languageService.getLanguageByLanguageCode(payload.getLanguage().getLanguageCode());
        payload.setLanguage(language);

        payload.setImgUrl(album.getImgUrl());

        songRepository.save(payload);
    }

    /**
     * Update song.
     *
     * @param request the request
     * @param payload the payload
     */
    @Validated(OnSongUpdate.class)
    public void updateSong(final HttpServletRequest request, final @Valid Song payload) {

        updateSongDetails(request, payload, null, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        songRepository.save(payload);
    }

    /**
     * Update song.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     */
    @Validated(OnSongUpdate.class)
    public void updateSong(final HttpServletRequest request, final @Valid Song payload, MultipartFile image) {

        updateSongDetails(request, payload, image, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        songRepository.save(payload);
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

        song.setImgUrl(song.getAlbum().getImgUrl());

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

        return songRepository.findImgUrlUsingSurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(ErrorMessage.LYRICX_ERR_24, ErrorCode.LYRICX_ERR_24));
    }

    private String getSongImgUrl(Long id) {

        return songRepository.findImgUrlUsingId(id).orElseThrow(() -> new NotFoundException(ErrorMessage.LYRICX_ERR_24, ErrorCode.LYRICX_ERR_24));
    }

//    private void convertPayloadToSong(Song payload, HttpServletRequest request) {
//
//
//
//    }

    private void updateSongDetails(final HttpServletRequest request, final Song payload, @Nullable final MultipartFile image, Consumer<Contributor> contributorStatus) {

        Song oldSong = getSongBySurrogateKey(payload.getSurrogateKey());
        payload.setId(oldSong.getId());

        if (image != null)
        {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.SONG_FOLDER);

            String oldImgUrl = oldSong.getImgUrl();

            //delete old song image from S3 bucket
            if (oldImgUrl != null && !oldImgUrl.equals(songImageUrl) && !oldImgUrl.equals(oldSong.getAlbum().getImgUrl()))
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

            //if old album album art is same as song's current album art then set new album art from album when album is set
            if (image == null && oldSong.getAlbum().getImgUrl().equals(oldSong.getImgUrl()))
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

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
        contributorStatus.accept(contributor);
        payload.setLastModifiedBy(contributor);
    }

}
