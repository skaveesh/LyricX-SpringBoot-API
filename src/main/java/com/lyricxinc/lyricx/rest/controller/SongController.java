package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Song;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("song")
public interface SongController {

    @PostMapping("add")
    ResponseEntity<HttpResponseData> addSong(HttpServletRequest request, @RequestBody Song payload);

    @PutMapping("update/details")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, @RequestBody Song payload);

    @PutMapping("update/albumart")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, @RequestPart Song payload, MultipartFile image);

    @DeleteMapping("remove/albumart")
    ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, @RequestBody Song payload);

    @DeleteMapping("remove")
    ResponseEntity<HttpResponseData> removeSong(HttpServletRequest request, @RequestBody Song payload);

}
