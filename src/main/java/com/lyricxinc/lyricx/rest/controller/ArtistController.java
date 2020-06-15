package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Artist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("artist")
public interface ArtistController {

    @PostMapping("create")
    ResponseEntity<HttpResponseData> createArtist(HttpServletRequest request, @RequestBody Artist payload, MultipartFile image);

    @PutMapping("update/details")
    ResponseEntity<HttpResponseData> updateArtist(HttpServletRequest request, @RequestBody Artist payload);

    @PutMapping("update/image")
    ResponseEntity<HttpResponseData> updateArtistImage(HttpServletRequest request, @RequestBody Artist payload, MultipartFile image);

    @DeleteMapping("delete/image")
    ResponseEntity<HttpResponseData> removeArtistImage(HttpServletRequest request, long artistId);

    @DeleteMapping("delete")
    ResponseEntity<HttpResponseData> removeArtist(HttpServletRequest request, long artistId);

}
