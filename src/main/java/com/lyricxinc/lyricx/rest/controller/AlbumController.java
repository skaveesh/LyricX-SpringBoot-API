package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("album")
public interface AlbumController {
    @PostMapping("add")
    ResponseEntity<HttpResponseData> addAlbum(HttpServletRequest request, long artistId, String name, String year, MultipartFile image);

    @PostMapping("update/details")
    ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, long albumId, long artistId, String name, String year);

    @PostMapping("update/image")
    ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, long albumId, MultipartFile image);

    @PostMapping("remove")
    ResponseEntity<HttpResponseData> removeAlbum(HttpServletRequest request, long albumId);
}
