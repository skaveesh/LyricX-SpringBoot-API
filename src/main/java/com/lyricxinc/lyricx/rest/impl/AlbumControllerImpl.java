package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.rest.controller.AlbumController;
import com.lyricxinc.lyricx.service.AlbumService;
import com.lyricxinc.lyricx.service.AmazonClientService;
import com.lyricxinc.lyricx.service.ArtistService;
import com.lyricxinc.lyricx.service.ContributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.Year;

@RestController
public class AlbumControllerImpl implements AlbumController {

    private final AmazonClientService amazonClientService;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final ContributorService contributorService;
    private final HttpResponse httpResponse;

    @Autowired
    AlbumControllerImpl(AmazonClientService amazonClientService, AlbumService albumService, ContributorService contributorService, ArtistService artistService, HttpResponse httpResponse) {
        this.amazonClientService = amazonClientService;
        this.albumService = albumService;
        this.contributorService = contributorService;
        this.artistService = artistService;
        this.httpResponse = httpResponse;
    }

    @Override
    public ResponseEntity<HttpResponseData> addAlbum(HttpServletRequest request, long artistId, String name, String year, MultipartFile image) {

        Artist artist = artistService.getArtistById(artistId);

        if (artist != null) {
            Contributor contributor = contributorService.getContributorById((String) request.getSession().getAttribute("userId"));
            String imgUrl = this.amazonClientService.uploadFile(image, AmazonClientService.S3BucketFolders.ALBUM_FOLDER);
            Album album = new Album(artist, Year.parse(year), name, imgUrl, contributor, contributor.isSeniorContributor());
            albumService.addAlbum(album);
        } else
            throw new ForbiddenCustomException("Requested artist cannot be found.");

        return httpResponse.returnResponse("Album created successfully.", "", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, long albumId, long artistId, String name, String year) {

        Album album = albumService.getAlbum(albumId);

        if(album != null){

        }else {
            throw new ForbiddenCustomException("Requested album cannot be found.");
        }

        return null;
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, long albumId, MultipartFile image) {
        return null;
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbum(HttpServletRequest request, long albumId) {
        return null;
    }
}
