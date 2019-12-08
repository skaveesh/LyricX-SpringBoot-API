package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Song;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("song")
public interface SongController {

    @PostMapping("add")
    ResponseEntity<HttpResponseData> addSong(HttpServletRequest request, final @Valid @RequestBody Song payload);

    @PutMapping("update/details")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, final @Valid @RequestBody Song payload);

    @PutMapping("update/albumart")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, final @Valid @RequestBody Song payload, MultipartFile image);

    @DeleteMapping("remove/albumart")
    ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, final @Valid @RequestBody Song payload);

    @DeleteMapping("remove")
    ResponseEntity<HttpResponseData> removeSong(HttpServletRequest request, final @Valid @RequestBody Song payload);

}
