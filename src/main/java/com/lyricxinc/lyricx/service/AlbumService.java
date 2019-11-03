package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
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
import java.util.UUID;

@Service
@Validated
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AmazonClientService amazonClientService;
    private final ContributorService contributorService;
    private final ArtistService artistService;

    @Value("${com.lyricxinc.lyricx.album-image-url}")
    String albumDefaultImageUrl;

    @Autowired
    public AlbumService(AmazonClientService amazonClientService, AlbumRepository albumRepository, ContributorService contributorService, ArtistService artistService) {

        this.amazonClientService = amazonClientService;
        this.albumRepository = albumRepository;
        this.contributorService = contributorService;
        this.artistService = artistService;
    }

    public Album getAlbumById(long id) {

        Album album = this.albumRepository.findById(id).orElse(null);

        if (album == null)
            throw new ForbiddenCustomException("Requested album cannot be found.");

        return album;
    }

    public Album getAlbumBySurrogateKey(String surrogateKey) {

        Album album = this.albumRepository.findBySurrogateKey(surrogateKey);

        if (album == null)
            throw new ForbiddenCustomException("Requested album cannot be found.");

        return album;
    }

    public List<Album> searchAlbums(String keyword) {

        return this.albumRepository.findTop20ByNameIgnoreCaseContainingOrderByNameAsc(keyword);
    }

    private void setArtistThroughSurrogateKey(Album payload){

        String artistSurrogateKey = payload.getArtist().getSurrogateKey();

        if(artistSurrogateKey != null)
            payload.setArtist(artistService.getAlbumBySurrogateKey(artistSurrogateKey));
    }

    @Validated(OnAlbumCreate.class)
    public void addAlbum(HttpServletRequest request, final @Valid Album payload, MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        payload.setAddedBy(contributor);
        payload.setSurrogateKey(UUID.randomUUID().toString().replace("-", ""));
        payload.setLastModifiedBy(contributor);

        if(image !=null) {
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);
            payload.setImgUrl(imgUrl);
        }else
            payload.setImgUrl(albumDefaultImageUrl);

        this.albumRepository.save(payload);
    }

    @Validated(OnAlbumUpdate.class)
    public void updateAlbum(HttpServletRequest request, @Valid Album payload) {

        Album oldAlbum = getAlbumBySurrogateKey(payload.getSurrogateKey());
        payload.setId(oldAlbum.getId());

        if (payload.getArtist() == null || payload.getArtist().getId() == null)
            payload.setArtist(oldAlbum.getArtist());
        else
            this.setArtistThroughSurrogateKey(payload);

        if (payload.getAddedBy() == null || payload.getAddedBy().getId() == null)
            payload.setAddedBy(oldAlbum.getAddedBy());

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, payload);

        payload.setLastModifiedBy(contributor);

        albumRepository.save(payload);
    }

    public void updateAlbum(HttpServletRequest request, Album payload, MultipartFile payloadImage) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, payload);

        String imgUrl = this.amazonClientService.uploadFile(payloadImage, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);

        //delete old album image from S3 bucket
        amazonClientService.deleteFileFromS3Bucket(payload.getImgUrl(), AmazonClientService.S3BucketFolders.ALBUM_FOLDER);

        payload.setImgUrl(imgUrl);

        albumRepository.save(payload);
    }

    public void removeAlbumArt(long id) {
        //TODO
    }

    public void removeAlbum(long id) {
//
//        albumRepository.deleteById(id);
    }

}
