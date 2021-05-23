package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.core.dto.SongCreateUpdateRequestDTO;
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
     * Save song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @PutMapping("save/details")
    ResponseEntity<HttpResponseData> saveSong(HttpServletRequest request, @RequestBody SongCreateUpdateRequestDTO payload);

    /**
     * Save song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PutMapping("save/albumart")
    ResponseEntity<HttpResponseData> saveSong(HttpServletRequest request, @RequestBody SongCreateUpdateRequestDTO payload, MultipartFile image);

    /**
     * Create song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @PutMapping("create/details")
    ResponseEntity<HttpResponseData> createSong(HttpServletRequest request, @RequestBody SongCreateUpdateRequestDTO payload);

    /**
     * Create song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PutMapping("create/albumart")
    ResponseEntity<HttpResponseData> createSong(HttpServletRequest request, @RequestBody SongCreateUpdateRequestDTO payload, MultipartFile image);

    /**
     * Update song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @PostMapping("update/details")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, @RequestBody SongCreateUpdateRequestDTO payload);

    /**
     * Update song response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PostMapping("update/albumart")
    ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, @RequestBody SongCreateUpdateRequestDTO payload, MultipartFile image);

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
