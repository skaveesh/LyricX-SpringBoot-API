package com.lyricxinc.lyricx.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("artist")
public interface ArtistController {
    @PostMapping("add")
    void addArtist(String name, MultipartFile image, String addedById);
}
