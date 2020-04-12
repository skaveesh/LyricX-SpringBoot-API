package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumCreate;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumUpdate;
import com.lyricxinc.lyricx.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.function.Consumer;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

@Validated
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AmazonClientService amazonClientService;
    private final ContributorService contributorService;
    private final ArtistService artistService;

    @Value("${com.lyricxinc.lyricx.albumImageUrl}")
    String albumDefaultImageUrl;

    @Autowired
    public AlbumService(AmazonClientService amazonClientService, AlbumRepository albumRepository, ContributorService contributorService, ArtistService artistService) {

        this.amazonClientService = amazonClientService;
        this.albumRepository = albumRepository;
        this.contributorService = contributorService;
        this.artistService = artistService;
    }

    public Album getAlbumById(final Long id) {
        return albumRepository.findById(id).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.LYRICX_ERR_11, ErrorCode.LYRICX_ERR_11));
    }

    public Album getAlbumBySurrogateKey(final String surrogateKey) {
        return albumRepository.findBySurrogateKey(surrogateKey).orElseThrow(() ->
                new ForbiddenException(ErrorMessage.LYRICX_ERR_11, ErrorCode.LYRICX_ERR_11));
    }

    /**
     * @param keyword
     * @return
     */
    public List<Album> searchAlbums(final String keyword) {
        return this.albumRepository.findTop20ByNameIgnoreCaseContainingOrderByNameAsc(keyword);
    }

    /**
     * @param request
     * @param payload
     * @param image
     */
    @Validated(OnAlbumCreate.class)
    public void addAlbum(final HttpServletRequest request, final @Valid Album payload, MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        payload.setAddedBy(contributor);
        payload.setLastModifiedBy(contributor);

        payload.setArtist(artistService.getArtistBySurrogateKey(payload.getArtist().getSurrogateKey()));

        if (image != null) {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);
            payload.setImgUrl(imgUrl);
        } else
        {
            payload.setImgUrl(albumDefaultImageUrl);
        }

        this.albumRepository.save(payload);
    }

    @Validated(OnAlbumUpdate.class)
    public void updateAlbum(final HttpServletRequest request, final @Valid Album payload) {

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        albumRepository.save(payload);
    }

    @Validated(OnAlbumUpdate.class)
    public void updateAlbum(final HttpServletRequest request, final @Valid Album payload, MultipartFile payloadImage) {

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        String imgUrl = this.amazonClientService.uploadFile(payloadImage, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);

        String oldImgUrl = null;

        try
        {
            oldImgUrl = getAlbumImgUrl(payload.getSurrogateKey());
        } finally
        {
            //delete old song image from S3 bucket
            if (oldImgUrl != null)
            {
                this.amazonClientService.deleteFileFromS3Bucket(oldImgUrl, AmazonClientService.S3BucketFolders.SONG_FOLDER);
            }
        }
        payload.setImgUrl(imgUrl);

        albumRepository.save(payload);
    }

    public void removeAlbumArt(Long id) {
        //TODO
    }

    public void removeAlbum(Long id) {
        //
        //        albumRepository.deleteById(id);
    }

    @Validated(OnAlbumUpdate.class)
    public void resetAlbumArt(final HttpServletRequest request, final @Valid Album payload) {

        updateAlbumDetails(request, payload, cont -> contributorService.checkNonSeniorContributorEditsVerifiedContent(cont, payload));

        payload.setImgUrl(albumDefaultImageUrl);

        albumRepository.save(payload);
    }

    private void setArtistThroughSurrogateKey(final Album payload) {

        String artistSurrogateKey = payload.getArtist().getSurrogateKey();

        if (artistSurrogateKey != null)
        {
            payload.setArtist(artistService.getArtistBySurrogateKey(artistSurrogateKey));
        }
    }

    private String getAlbumImgUrl(String surrogateKey){
        return albumRepository.findImgUrlUsingSurrogateKey(surrogateKey).orElseThrow(() ->
                new NotFoundException(ErrorMessage.LYRICX_ERR_25, ErrorCode.LYRICX_ERR_25));
    }

    private void updateAlbumDetails(final HttpServletRequest request, final Album payload, Consumer<Contributor> contributorStatus) {

        Album oldAlbum = getAlbumBySurrogateKey(payload.getSurrogateKey());
        payload.setId(oldAlbum.getId());

        if (payload.getArtist() == null || payload.getArtist().getId() == null)
        {
            payload.setArtist(oldAlbum.getArtist());
        }else
        {
            this.setArtistThroughSurrogateKey(payload);
        }

        if (payload.getAddedBy() == null || payload.getAddedBy().getId() == null)
        {
            payload.setAddedBy(oldAlbum.getAddedBy());
        }

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
        contributorStatus.accept(contributor);
        payload.setLastModifiedBy(contributor);
    }

}
