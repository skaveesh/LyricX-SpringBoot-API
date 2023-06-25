package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.dto.AlbumDTO;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Album controller.
 */
@RequestMapping("album")
public interface AlbumController {

    /**
     * Gets album.
     *
     * @param surrogateKey the surrogate key
     * @return the album
     */
    @GetMapping("get")
    ResponseEntity<HttpResponseData> getAlbum(@RequestParam("surrogateKey") String surrogateKey);

    /**
     * Search albums response entity.
     *
     * @param query      the query
     * @param pageNumber the page number
     * @param pageSize   the page size
     * @return the response entity
     */
    @GetMapping("search")
    ResponseEntity<HttpResponseData> searchAlbums(@RequestParam String query, @RequestParam Integer pageNumber, @RequestParam Integer pageSize);

    /**
     * Create album response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PutMapping("create")
    ResponseEntity<HttpResponseData> createAlbum(HttpServletRequest request, @RequestPart("payload") AlbumDTO payload, @RequestPart("image") MultipartFile image);

    /**
     * Save album response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PostMapping("save")
    ResponseEntity<HttpResponseData> saveAlbum(HttpServletRequest request, @RequestPart("payload") AlbumDTO payload, @RequestPart(name = "image", required = false) MultipartFile image);

    /**
     * Update album response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @PostMapping("update/details")
    ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, @RequestBody AlbumDTO payload);

    /**
     * Update album response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PostMapping("update/details/image")
    ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, @RequestPart("payload") AlbumDTO payload, @RequestPart("image") MultipartFile image);

    /**
     * Remove album art response entity.
     *
     * @param request the request
     * @param payload the payload
     * @return the response entity
     */
    @DeleteMapping("remove/albumart")
    ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, @RequestBody AlbumDTO payload);

    /**
     * Remove album response entity.
     *
     * @param request the request
     * @param albumId the album id
     * @return the response entity
     */
    @DeleteMapping("remove")
    ResponseEntity<HttpResponseData> removeAlbum(HttpServletRequest request, long albumId);

}
