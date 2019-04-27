package com.lyricxinc.lyricx.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("artist")
public interface ArtistController {
    @PostMapping("add")
    void addArtist(HttpServletRequest request, String name, MultipartFile image);

    @PostMapping("update/details")
    void updateArtistName(HttpServletRequest request, long artistId, String name);

    @PostMapping("update/image")
    void updateArtistImage(HttpServletRequest request, long artistId, MultipartFile image);
}
