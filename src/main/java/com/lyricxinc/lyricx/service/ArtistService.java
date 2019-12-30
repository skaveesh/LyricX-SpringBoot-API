package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
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
import java.util.UUID;
import java.util.function.Consumer;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ContributorService contributorService;
    private final AmazonClientService amazonClientService;

    @Value("${com.lyricxinc.lyricx.artistImageUrl}")
    String artistDefaultImageUrl;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, ContributorService contributorService, AmazonClientService amazonClientService) {

        this.artistRepository = artistRepository;
        this.contributorService = contributorService;
        this.amazonClientService = amazonClientService;
    }

    public Artist getArtistById(long id) {
        return artistRepository.findById(id).orElseThrow(() -> new ForbiddenException(ErrorMessage.LYRICX_ERR_12, ErrorCode.LYRICX_ERR_12));
    }

    public Artist getArtistBySurrogateKey(String surrogateKey) {

        Artist artist = this.artistRepository.findBySurrogateKey(surrogateKey);

        if (artist == null)
            throw new ForbiddenException(ErrorMessage.LYRICX_ERR_12, ErrorCode.LYRICX_ERR_12);

        return artist;
    }

    @Validated(OnArtistCreate.class)
    public void addArtist(final HttpServletRequest request, final @Valid Artist payload, final MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        payload.setAddedBy(contributor);
        payload.setSurrogateKey(UUID.randomUUID().toString().replace("-", ""));
        payload.setLastModifiedBy(contributor);

        if (image != null) {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);
            payload.setImgUrl(imgUrl);
        } else
            payload.setImgUrl(artistDefaultImageUrl);

        this.artistRepository.save(payload);
    }

    @Validated(OnArtistUpdate.class)
    public void updateArtist(final HttpServletRequest request, final @Valid Artist payload) {

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        artistRepository.save(payload);
    }

    @Validated(OnArtistUpdate.class)
    public void updateArtist(final HttpServletRequest request, final @Valid Artist payload, final MultipartFile image) {

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

        //delete old artist image from S3 bucket
        this.amazonClientService.deleteFileFromS3Bucket(payload.getImgUrl(), AmazonClientService.S3BucketFolders.ARTIST_FOLDER);

        payload.setImgUrl(imgUrl);

        artistRepository.save(payload);
    }

    public void removeImage(long id) {
        //TODO
    }

    public void removeArtist(long id) {

        artistRepository.deleteById(id);
    }

    private void updateAlbumDetails(final HttpServletRequest request, final Artist payload, Consumer<Contributor> contributorStatus) {

        Artist oldArtist = this.getArtistBySurrogateKey(payload.getSurrogateKey());
        payload.setId(oldArtist.getId());

        if (payload.getAddedBy() == null || payload.getAddedBy().getId() == null)
            payload.setAddedBy(oldArtist.getAddedBy());

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
        contributorStatus.accept(contributor);
        payload.setLastModifiedBy(contributor);
    }

}
