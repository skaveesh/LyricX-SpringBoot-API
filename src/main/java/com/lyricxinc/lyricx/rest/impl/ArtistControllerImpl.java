package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.rest.controller.ArtistController;
import com.lyricxinc.lyricx.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.lyricxinc.lyricx.core.constant.Constants.SuccessCode;
import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage;

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
    public ResponseEntity<HttpResponseData> createArtist(final HttpServletRequest request, @Valid @RequestBody Artist payload, MultipartFile image) {

        artistService.addArtist(request, payload, image);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ARTIST_CREATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateArtist(HttpServletRequest request, @Valid @RequestBody Artist payload) {

        artistService.updateArtist(request, payload);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ARTIST_UPDATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateArtistImage(HttpServletRequest request, @Valid @RequestBody Artist payload, MultipartFile image) {

        artistService.updateArtist(request, payload, image);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ARTIST_IMAGE_UPDATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeArtistImage(HttpServletRequest request, long artistId) {
        //TODO
        return null;
    }

    @Override
    public ResponseEntity<HttpResponseData> removeArtist(HttpServletRequest request, long artistId) {

        //TODO
        return null;
    }

}
