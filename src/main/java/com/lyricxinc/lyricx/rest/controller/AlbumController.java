package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Album;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RequestMapping("album")
public interface AlbumController {

    @PostMapping("add")
    ResponseEntity<HttpResponseData> addAlbum(HttpServletRequest request, UUID artistId, String name, String year, MultipartFile image);

    @GetMapping("search")
    ResponseEntity<HttpResponseData> searchAlbums(String keyword);

    @PutMapping("update/details")
    ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, @RequestBody Album payload);

    @PutMapping("update/image")
    ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, UUID albumId, MultipartFile image);

    @DeleteMapping("remove/albumart")
    ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, UUID albumId);

    @DeleteMapping("remove")
    ResponseEntity<HttpResponseData> removeAlbum(HttpServletRequest request, UUID albumId);

}
