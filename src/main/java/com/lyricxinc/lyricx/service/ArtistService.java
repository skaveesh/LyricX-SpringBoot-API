package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.validator.group.OnArtistCreate;
import com.lyricxinc.lyricx.model.validator.group.OnArtistUpdate;
import com.lyricxinc.lyricx.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.function.Consumer;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

/**
 * The type Artist service.
 */
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ContributorService contributorService;
    private final AmazonClientService amazonClientService;

    /**
     * The Artist default image url.
     */
    @Value("${com.lyricxinc.lyricx.artistImageUrl}")
    String artistDefaultImageUrl;

    /**
     * Instantiates a new Artist service.
     *
     * @param artistRepository    the artist repository
     * @param contributorService  the contributor service
     * @param amazonClientService the amazon client service
     */
    @Autowired
    public ArtistService(ArtistRepository artistRepository, ContributorService contributorService, AmazonClientService amazonClientService) {

        this.artistRepository = artistRepository;
        this.contributorService = contributorService;
        this.amazonClientService = amazonClientService;
    }

    /**
     * Gets artist by id.
     *
     * @param id the id
     * @return the artist by id
     */
    public Artist getArtistById(long id) {

        return artistRepository.findById(id).orElseThrow(() -> new ForbiddenException(ErrorMessage.LYRICX_ERR_12, ErrorCode.LYRICX_ERR_12));
    }

    /**
     * Gets artist by surrogate key.
     *
     * @param surrogateKey the surrogate key
     * @return the artist by surrogate key
     */
    public Artist getArtistBySurrogateKey(String surrogateKey) {

        return artistRepository.findBySurrogateKey(surrogateKey).orElseThrow(() -> new ForbiddenException(ErrorMessage.LYRICX_ERR_12, ErrorCode.LYRICX_ERR_12));
    }

    /**
     * Add artist.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     */
    @Validated(OnArtistCreate.class)
    public void addArtist(final HttpServletRequest request, final @Valid Artist payload, final MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        payload.setAddedBy(contributor);
        payload.setLastModifiedBy(contributor);

        if (image != null)
        {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);
            payload.setImgUrl(imgUrl);
        }
        else
            payload.setImgUrl(artistDefaultImageUrl);

        this.artistRepository.save(payload);
    }

    /**
     * Update artist.
     *
     * @param request the request
     * @param payload the payload
     */
    @Validated(OnArtistUpdate.class)
    public void updateArtist(final HttpServletRequest request, final @Valid Artist payload) {

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        artistRepository.save(payload);
    }

    /**
     * Update artist.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     */
    @Validated(OnArtistUpdate.class)
    public void updateArtist(final HttpServletRequest request, final @Valid Artist payload, final MultipartFile image) {

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

        String oldImgUrl = null;

        try
        {
            oldImgUrl = getArtistImgUrl(payload.getSurrogateKey());
        } finally
        {
            //delete old song image from S3 bucket
            if (oldImgUrl != null)
            {
                this.amazonClientService.deleteFileFromS3Bucket(oldImgUrl, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);
            }
        }

        payload.setImgUrl(imgUrl);

        artistRepository.save(payload);
    }

    /**
     * Remove image.
     *
     * @param id the id
     */
    public void removeImage(long id) {
        //TODO
    }

    /**
     * Remove artist.
     *
     * @param id the id
     */
    public void removeArtist(long id) {

        artistRepository.deleteById(id);
    }

    private String getArtistImgUrl(String surrogateKey) {

        return artistRepository.findImgUrlUsingSurrogateKey(surrogateKey).orElseThrow(() -> new NotFoundException(ErrorMessage.LYRICX_ERR_26, ErrorCode.LYRICX_ERR_26));
    }

    private void updateAlbumDetails(final HttpServletRequest request, final Artist payload, Consumer<Contributor> contributorStatus) {

        Artist oldArtist = this.getArtistBySurrogateKey(payload.getSurrogateKey());
        payload.setId(oldArtist.getId());

        if (payload.getAddedBy() == null || payload.getAddedBy().getId() == null)
        {
            payload.setAddedBy(oldArtist.getAddedBy());
        }

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
        contributorStatus.accept(contributor);
        payload.setLastModifiedBy(contributor);
    }

}
