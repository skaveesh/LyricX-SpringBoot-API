package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.Year;
import java.util.UUID;

@Service
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

    public void addAlbum(HttpServletRequest request, long artistId, String name, Year year, MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        Artist artist = artistService.getArtistById(artistId);

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);

        Album album = new Album(artist, year, name, imgUrl, contributor, contributor.isSeniorContributor(), UUID.randomUUID().toString().replace("-", ""));

        this.albumRepository.save(album);
    }

    public void updateAlbum(HttpServletRequest request, Album album, long artistId, String name, Year year) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, album);

        Artist artist = artistService.getArtistById(artistId);

        album.setArtist(artist);
        album.setName(name);
        album.setYear(year);

        albumRepository.save(album);
    }

    public void updateAlbum(HttpServletRequest request, Album album, MultipartFile image) {

        Contributor contributor = contributorService.getContributorByHttpServletRequest(request);

        contributorService.checkNonSeniorContributorEditsVerifiedContent(contributor, album);

        String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);

        //delete old album image from S3 bucket
        amazonClientService.deleteFileFromS3Bucket(album.getImgUrl(), AmazonClientService.S3BucketFolders.ALBUM_FOLDER);

        album.setImgUrl(imgUrl);

        albumRepository.save(album);
    }

    public void removeAlbumArt(long id){
        //TODO
    }

    public void removeAlbum(long id) {

        albumRepository.deleteById(id);
    }
}
