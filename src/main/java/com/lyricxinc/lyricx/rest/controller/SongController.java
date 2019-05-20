package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("song")
public interface SongController {
    @PostMapping("add")
    ResponseEntity<HttpResponseData> addSong(HttpServletRequest request, String name, long albumId, String guitarKey, String beat, short languageId, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink);

    @PutMapping("update/details")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, long songId, String name, long albumId, String guitarKey, String beat, short languageId, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink);

    @PutMapping("update/albumart")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, long songId, MultipartFile image);

    @DeleteMapping("remove/albumart")
    ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, long songId);

    @DeleteMapping("remove")
    ResponseEntity<HttpResponseData> removeSong(HttpServletRequest request, long songId);
}
