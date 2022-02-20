package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.dto.ArtistDTO;
import com.lyricxinc.lyricx.core.dto.ArtistPageableDTO;
import com.lyricxinc.lyricx.core.exception.EntityConversionException;
import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.rest.controller.ArtistController;
import com.lyricxinc.lyricx.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.LYRICX_ERR_35;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.LYRICX_ERR_36;
import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage.*;

@RestController
public class ArtistControllerImpl implements ArtistController {

    private final ArtistService artistService;
    private final HttpResponse httpResponse;
    private final ConversionService conversionService;

    @Autowired
    ArtistControllerImpl(ArtistService artistService, HttpResponse httpResponse, ConversionService conversionService) {

        this.artistService = artistService;
        this.httpResponse = httpResponse;
        this.conversionService = conversionService;
    }

    @Override
    public ResponseEntity<HttpResponseData> getArtist(String surrogateKey) {

        Artist artist = artistService.getArtistBySurrogateKey(surrogateKey);
        ArtistDTO dto = asArtistDTO(artist);

        return httpResponse.returnResponse(HttpStatus.OK, SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> searchArtists(@RequestParam String query, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

        Page<Artist> artistPages = artistService.searchArtists(query, pageNumber, pageSize);

        List<ArtistDTO> dtoArtistList = artistPages.stream().map(this::asArtistDTO).collect(Collectors.toList());
        ArtistPageableDTO dto = new ArtistPageableDTO(artistPages.getTotalPages(), artistPages.getNumber(), artistPages.getSize(), artistPages.getTotalElements(), dtoArtistList);

        return httpResponse.returnResponse(HttpStatus.OK, SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> createArtist(final HttpServletRequest request, @RequestPart("payload") ArtistDTO payload, @RequestPart("image") MultipartFile image) {

        artistService.addArtist(request, conversionService.convert(payload, Artist.class), image, payload.getGenreIdList());

        return httpResponse.returnResponse(HttpStatus.OK, ARTIST_CREATE_SUCCESS.getSuccessMessage(), null, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> saveArtist(HttpServletRequest request, @RequestPart("payload") ArtistDTO payload, @RequestPart(name = "image", required = false) MultipartFile image) {

        Artist albumPayload = asArtist(payload);
        Artist savedAlbum = artistService.saveArtist(request, albumPayload, image, payload.getGenreIdList());
        ArtistDTO dto = asArtistDTO(savedAlbum);

        return httpResponse.returnResponse(HttpStatus.OK, ARTIST_SAVE_SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateArtist(HttpServletRequest request, @RequestBody ArtistDTO payload) {

        artistService.updateArtist(request, conversionService.convert(payload, Artist.class), payload.getGenreIdList());

        return httpResponse.returnResponse(HttpStatus.OK, ARTIST_UPDATE_SUCCESS.getSuccessMessage(), null, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateArtistImage(HttpServletRequest request, @RequestPart("payload") ArtistDTO payload, @RequestPart("image") MultipartFile image) {

        artistService.updateArtist(request, conversionService.convert(payload, Artist.class), image, payload.getGenreIdList());

        return httpResponse.returnResponse(HttpStatus.OK, ARTIST_IMAGE_UPDATE_SUCCESS.getSuccessMessage(), null, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeArtistImage(HttpServletRequest request, long artistId) {
        //TODO
        return null;
    }

    @Override
    public ResponseEntity<HttpResponseData> removeArtist(HttpServletRequest request, long artistId) {

        //TODO
        return null;
    }

    private Artist asArtist(final ArtistDTO requestDTO) {
        return Optional.ofNullable(conversionService.convert(requestDTO, Artist.class)).orElseThrow(() -> new EntityConversionException(LYRICX_ERR_35));
    }

    private ArtistDTO asArtistDTO(final Artist artist) {
        return Optional.ofNullable(conversionService.convert(artist, ArtistDTO.class)).orElseThrow(() -> new EntityConversionException(LYRICX_ERR_36));
    }
}
