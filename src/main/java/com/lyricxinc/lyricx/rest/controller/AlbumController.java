package com.lyricxinc.lyricx.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("album")
public interface AlbumController {
    @PostMapping("add")
    void addAlbum(long artistId, String name, String year, MultipartFile image, long addedById);
}
