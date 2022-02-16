package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.dto.AlbumDTO;
import com.lyricxinc.lyricx.core.exception.EntityConversionException;
import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.rest.controller.AlbumController;
import com.lyricxinc.lyricx.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.LYRICX_ERR_35;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.LYRICX_ERR_36;
import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage;
import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage.SUCCESS;

@RestController
public class AlbumControllerImpl implements AlbumController {

    private final AlbumService albumService;
    private final HttpResponse httpResponse;
    private final ConversionService conversionService;

    @Autowired
    AlbumControllerImpl(AlbumService albumService, HttpResponse httpResponse, ConversionService conversionService) {

        this.albumService = albumService;
        this.httpResponse = httpResponse;
        this.conversionService = conversionService;
    }

    @Override
    public ResponseEntity<HttpResponseData> getAlbum(String surrogateKey) {

        Album album = albumService.getAlbumBySurrogateKey(surrogateKey);
        AlbumDTO dto = asAlbumDTO(album);

        return httpResponse.returnResponse(HttpStatus.OK, SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> createAlbum(final HttpServletRequest request, @RequestPart("payload") AlbumDTO payload, @RequestPart("image") MultipartFile image) {

        albumService.addAlbum(request, conversionService.convert(payload, Album.class), image);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ALBUM_CREATE_SUCCESS.getSuccessMessage(), null, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> searchAlbums(final String keyword) {

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SUCCESS.getSuccessMessage(), null, albumService.searchAlbums(keyword));
    }

    @Override
    public ResponseEntity<HttpResponseData> saveAlbum(HttpServletRequest request, @RequestPart("payload") AlbumDTO payload, @RequestPart(name = "image", required = false) MultipartFile image) {

        Album albumPayload = asAlbum(payload);
        Album savedAlbum = albumService.saveAlbum(request, albumPayload, image);
        AlbumDTO dto = asAlbumDTO(savedAlbum);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ALBUM_SAVE_SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, final @RequestBody AlbumDTO payload) {

        albumService.updateAlbum(request, conversionService.convert(payload, Album.class));

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ALBUM_UPDATE_SUCCESS.getSuccessMessage(), null, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateAlbum(HttpServletRequest request, @RequestPart("payload") AlbumDTO payload, @RequestPart("image") MultipartFile image) {

        albumService.updateAlbum(request, conversionService.convert(payload, Album.class), image);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ALBUM_ARTWORK_UPDATE_SUCCESS.getSuccessMessage(), null, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, final @RequestBody AlbumDTO payload) {

        albumService.resetAlbumArt(request, conversionService.convert(payload, Album.class));

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.ALBUM_ARTWORK_REMOVE_SUCCESS.getSuccessMessage(), null, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbum(HttpServletRequest request, long albumId) {

        //TODO
        return null;
    }

    private Album asAlbum(final AlbumDTO requestDTO) {
        return Optional.ofNullable(conversionService.convert(requestDTO, Album.class)).orElseThrow(() -> new EntityConversionException(LYRICX_ERR_35));
    }

    private AlbumDTO asAlbumDTO(final Album album) {
        return Optional.ofNullable(conversionService.convert(album, AlbumDTO.class)).orElseThrow(() -> new EntityConversionException(LYRICX_ERR_36));
    }
}
