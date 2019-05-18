package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.rest.controller.AlbumController;
import com.lyricxinc.lyricx.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.lyricxinc.lyricx.core.parser.YearParser.parseYear;

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
    public ResponseEntity<HttpResponseData> addAlbum(HttpServletRequest request, long artistId, String name, String year, MultipartFile image) {

        albumService.addAlbum(artistId, name, parseYear(year), image, (String) request.getSession().getAttribute("userId"));

        return httpResponse.returnResponse(HttpStatus.OK, "Album created successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, long albumId, long artistId, String name, String year) {

        Album album = albumService.getAlbum(albumId);

        albumService.updateAlbum(request, album, artistId, name, parseYear(year));

        return httpResponse.returnResponse(HttpStatus.OK, "Album updated successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, long albumId, MultipartFile image) {

        Album album = albumService.getAlbum(albumId);

        albumService.updateAlbum(request, album, image);

        return httpResponse.returnResponse(HttpStatus.OK, "Album artwork updated successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbum(HttpServletRequest request, long albumId) {
        return null;
    }

}
