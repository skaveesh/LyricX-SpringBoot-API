package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Album;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("album")
public interface AlbumController {

    @PutMapping("add")
    ResponseEntity<HttpResponseData> addAlbum(HttpServletRequest request, @RequestPart("payload") Album payload, @RequestPart("image") MultipartFile image);

    @GetMapping("search")
    ResponseEntity<HttpResponseData> searchAlbums(String keyword);

    @PostMapping("update/details")
    ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, @RequestBody Album payload);

    @PostMapping("update/image")
    ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, @RequestPart("payload") Album payload, @RequestPart("image") MultipartFile image);

    @DeleteMapping("remove/albumart")
    ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, @RequestBody Album payload);

    @DeleteMapping("remove")
    ResponseEntity<HttpResponseData> removeAlbum(HttpServletRequest request, long albumId);

}
