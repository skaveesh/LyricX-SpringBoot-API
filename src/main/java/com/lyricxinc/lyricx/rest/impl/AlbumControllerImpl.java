package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.rest.controller.AlbumController;
import com.lyricxinc.lyricx.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
public class AlbumControllerImpl implements AlbumController {

    private final AlbumService albumService;
    private final HttpResponse httpResponse;

    @Autowired
    AlbumControllerImpl(AlbumService albumService, HttpResponse httpResponse) {

        this.albumService = albumService;
        this.httpResponse = httpResponse;
    }

    @Override
    public ResponseEntity<HttpResponseData> addAlbum(HttpServletRequest request, @Valid @RequestBody Album payload, MultipartFile image) {

        albumService.addAlbum(request, payload, image);

        return httpResponse.returnResponse(HttpStatus.OK, "Album created successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> searchAlbums(String keyword) {

        return httpResponse.returnResponse(HttpStatus.OK, "Success", albumService.searchAlbums(keyword));
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, final @Valid @RequestBody Album payload) {

        albumService.updateAlbum(request, payload);

        return httpResponse.returnResponse(HttpStatus.OK, "Album updated successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, final @Valid @RequestBody Album payload, MultipartFile image) {

        albumService.updateAlbum(request, payload, image);

        return httpResponse.returnResponse(HttpStatus.OK, "Album artwork updated successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, final @Valid @RequestBody Album payload) {

        albumService.resetAlbumArt(request, payload);

        return httpResponse.returnResponse(HttpStatus.OK, "Album artwork removed successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbum(HttpServletRequest request, long albumId) {

        //TODO
        return null;
    }

}
