package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.rest.controller.ArtistController;
import com.lyricxinc.lyricx.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class ArtistControllerImpl implements ArtistController {

    private final ArtistService artistService;
    private final HttpResponse httpResponse;

    @Autowired
    ArtistControllerImpl(ArtistService artistService, HttpResponse httpResponse) {

        this.artistService = artistService;
        this.httpResponse = httpResponse;
    }

    @Override
    public ResponseEntity<HttpResponseData> addArtist(HttpServletRequest request, String name, MultipartFile image) {

        artistService.addArtist(request, name, image);

        return httpResponse.returnResponse(HttpStatus.OK, "Artist created successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateArtistName(HttpServletRequest request, UUID artistId, String name) {

        Artist artist = artistService.getArtistById(artistId);
        artistService.updateArtist(request, artist, name);

        return httpResponse.returnResponse(HttpStatus.OK, "Artist updated successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateArtistImage(HttpServletRequest request, UUID artistId, MultipartFile image) {

        Artist artist = artistService.getArtistById(artistId);
        artistService.updateArtist(request, artist, image);

        return httpResponse.returnResponse(HttpStatus.OK, "Artist image updated successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeArtistImage(HttpServletRequest request, UUID artistId) {
        //TODO
        return null;
    }

    @Override
    public ResponseEntity<HttpResponseData> removeArtist(HttpServletRequest request, UUID artistId) {

        //TODO
        return null;
    }

}
