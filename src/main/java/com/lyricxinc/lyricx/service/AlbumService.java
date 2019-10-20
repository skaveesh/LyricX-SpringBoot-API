package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumCreate;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumUpdate;
import com.lyricxinc.lyricx.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AmazonClientService amazonClientService;
    private final ContributorService contributorService;
    private final ArtistService artistService;

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

    public Album getAlbumBySurrogateKey(String surrogateKey){

        Album album = this.albumRepository.findBySurrogateKey(surrogateKey);

        if (album == null)
            throw new ForbiddenCustomException("Requested album cannot be found.");

        return album;
    }

    public List<Album> searchAlbums(String keyword) {

        return this.albumRepository.findTop20ByNameIgnoreCaseContainingOrderByNameAsc(keyword);
    }

    @Validated(OnAlbumCreate.class)
    public void addAlbum(HttpServletRequest request, final @Valid Album payload, MultipartFile image) {

//        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
//
//        Artist artist = artistService.getArtistById(artistId);
//
//        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);
//
//        Album album = new Album(artist, year, name, imgUrl, contributor, contributor.isSeniorContributor(), UUID.randomUUID().toString().replace("-", ""));
//
//        this.albumRepository.save(album);
    }

    @Validated(OnAlbumUpdate.class)
    public void updateAlbum(HttpServletRequest request, final @Valid Album payload) {

        System.out.println(payload.getSurrogateKey());
        System.out.println(payload.getName());

        long albumToUpdateId = getAlbumBySurrogateKey(payload.getSurrogateKey()).getId();

        payload.setId(albumToUpdateId);


        albumRepository.save(payload);



//        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);
//
//        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, payload);
//
//
//
//        Artist artist = artistService.getArtistById(payload.getArtist().getId());
//
//        Album album = getAlbumById(payload.getId());
//
//        album.setArtist(artist);
//        album.setName(name);
//        album.setYear(year);
//
//        albumRepository.save(album);
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
