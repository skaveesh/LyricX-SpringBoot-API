package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RequestMapping("artist")
public interface ArtistController {

    @PostMapping("add")
    ResponseEntity<HttpResponseData> addArtist(HttpServletRequest request, String name, MultipartFile image);

    @PutMapping("update/details")
    ResponseEntity<HttpResponseData> updateArtistName(HttpServletRequest request, UUID artistId, String name);

    @PutMapping("update/image")
    ResponseEntity<HttpResponseData> updateArtistImage(HttpServletRequest request, UUID artistId, MultipartFile image);

    @DeleteMapping("delete/image")
    ResponseEntity<HttpResponseData> removeArtistImage(HttpServletRequest request, UUID artistId);

    @DeleteMapping("delete")
    ResponseEntity<HttpResponseData> removeArtist(HttpServletRequest request, UUID artistId);

}
