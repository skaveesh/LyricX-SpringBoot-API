package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.dto.SongDTO;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Song;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Song controller.
 */
@RequestMapping("song")
public interface SongController {

    /**
     * Gets song.
     *
     * @param surrogateKey the surrogate key
     * @return the song
     */
    @GetMapping("get")
    ResponseEntity<HttpResponseData> getSong(@RequestParam String surrogateKey);

    /**
     * Gets songs added by contributor.
     *
     * @param request    the request
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the songs added by contributor
     */
    @GetMapping("get/contributor")
    ResponseEntity<HttpResponseData> getSongsAddedByContributor(HttpServletRequest request, @RequestParam Integer pageNumber, @RequestParam Integer pageSize);

    /**
     * Save song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @PutMapping("save/details")
    ResponseEntity<HttpResponseData> saveSong(HttpServletRequest request, @RequestPart("payload") SongDTO payload);

    /**
     * Save song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PostMapping("save")
    ResponseEntity<HttpResponseData> saveSong(HttpServletRequest request, @RequestPart("payload") SongDTO payload, @RequestPart(name = "image", required = false) MultipartFile image);

    /**
     * Create song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @PutMapping("create/details")
    ResponseEntity<HttpResponseData> createSong(HttpServletRequest request, @RequestPart("payload") SongDTO payload);

    /**
     * Create song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PutMapping("create/albumart")
    ResponseEntity<HttpResponseData> createSong(HttpServletRequest request, @RequestPart("payload") SongDTO payload, @RequestPart("image") MultipartFile image);

    /**
     * Update song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @PostMapping("update/details")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, @RequestPart("payload") SongDTO payload);

    /**
     * Update song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PostMapping("update/albumart")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, @RequestPart("payload") SongDTO payload, @RequestPart("image") MultipartFile image);

    /**
     * Remove album art response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @DeleteMapping("remove/albumart")
    ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, @RequestBody Song payload);

    /**
     * Remove song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @DeleteMapping("remove")
    ResponseEntity<HttpResponseData> removeSong(HttpServletRequest request, @RequestBody Song payload);

}
