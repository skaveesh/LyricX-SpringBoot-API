package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.dto.ArtistDTO;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Artist controller.
 */
@RequestMapping("artist")
public interface ArtistController {

    /**
     * Gets artist.
     *
     * @param surrogateKey the surrogate key
     * @return the artist
     */
    @GetMapping("get")
    ResponseEntity<HttpResponseData> getArtist(@RequestParam("surrogateKey") String surrogateKey);

    /**
     * Create artist response entity.
     *
     * @param request the request
     * @param payload the payload
     * @param image   the image
     * @return the response entity
     */
    @PutMapping("create")
    ResponseEntity<HttpResponseData> createArtist(HttpServletRequest request, @RequestPart("payload") ArtistDTO payload, @RequestPart("image") MultipartFile image);

    /**
     * Update artist image response entity.
     *
     * @param request the request
     * @param payload the request payload
     * @param image   the image
     * @return the response entity
     */
    @PostMapping("save")
    ResponseEntity<HttpResponseData> saveArtist(HttpServletRequest request, @RequestPart("payload") ArtistDTO payload, @RequestPart(name = "image", required = false) MultipartFile image);

    /**
     * Update artist response entity.
     *
     * @param request        the request
     * @param requestPayload the request payload
     * @return the response entity
     */
    @PostMapping("update/details")
    ResponseEntity<HttpResponseData> updateArtist(HttpServletRequest request, @RequestBody ArtistDTO requestPayload);

    /**
     * Update artist image response entity.
     *
     * @param request        the request
     * @param requestPayload the request payload
     * @param image          the image
     * @return the response entity
     */
    @PostMapping("update/details/image")
    ResponseEntity<HttpResponseData> updateArtistImage(HttpServletRequest request, @RequestPart("payload") ArtistDTO requestPayload, @RequestPart("image") MultipartFile image);

    /**
     * Remove artist image response entity.
     *
     * @param request  the request
     * @param artistId the artist id
     * @return the response entity
     */
    @DeleteMapping("delete/image")
    ResponseEntity<HttpResponseData> removeArtistImage(HttpServletRequest request, long artistId);

    /**
     * Remove artist response entity.
     *
     * @param request  the request
     * @param artistId the artist id
     * @return the response entity
     */
    @DeleteMapping("delete")
    ResponseEntity<HttpResponseData> removeArtist(HttpServletRequest request, long artistId);

}
