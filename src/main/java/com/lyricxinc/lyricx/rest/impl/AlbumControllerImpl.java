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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.lyricxinc.lyricx.core.constant.Constants.SuccessCode;
import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage;

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
    public ResponseEntity<HttpResponseData> addAlbum(final HttpServletRequest request, @RequestPart("payload") Album payload, @RequestPart("image") MultipartFile image) {

        albumService.addAlbum(request, payload, image);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ALBUM_CREATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> searchAlbums(final String keyword) {

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SUCCESS, SuccessCode.LYRICX_SUC_00, albumService.searchAlbums(keyword));
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, final @RequestBody Album payload) {

        albumService.updateAlbum(request, payload);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ALBUM_UPDATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, @RequestPart("payload") Album payload, @RequestPart("image") MultipartFile image) {

        albumService.updateAlbum(request, payload, image);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ALBUM_ARTWORK_UPDATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, final @RequestBody Album payload) {

        albumService.resetAlbumArt(request, payload);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ALBUM_ARTWORK_REMOVE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbum(HttpServletRequest request, long albumId) {

        //TODO
        return null;
    }

}
