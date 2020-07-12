package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.core.dto.SongCreateUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("song")
public interface SongController {

    @PutMapping("create")
    ResponseEntity<HttpResponseData> createSong(HttpServletRequest request, @RequestBody SongCreateUpdateRequest payload);

    @PostMapping("update/details")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, @RequestBody SongCreateUpdateRequest payload);

    @PostMapping("update/albumart")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, @RequestBody SongCreateUpdateRequest payload, MultipartFile image);

    @DeleteMapping("remove/albumart")
    ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, @RequestBody Song payload);

    @DeleteMapping("remove")
    ResponseEntity<HttpResponseData> removeSong(HttpServletRequest request, @RequestBody Song payload);

}
