package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.dto.SongDTO;
import com.lyricxinc.lyricx.core.exception.EntityConversionException;
import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.rest.controller.SongController;
import com.lyricxinc.lyricx.service.SongService;
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
import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage.*;

/**
 * The type Song controller.
 */
@RestController
public class SongControllerImpl implements SongController {

    private final SongService songService;
    private final HttpResponse httpResponse;
    private final ConversionService conversionService;

    /**
     * Instantiates a new Song controller.
     *
     * @param httpResponse      the http response
     * @param songService       the song service
     * @param conversionService the conversion service
     */
    @Autowired
    public SongControllerImpl(HttpResponse httpResponse, SongService songService, ConversionService conversionService) {

        this.httpResponse = httpResponse;
        this.songService = songService;
        this.conversionService = conversionService;
    }

    @Override
    public ResponseEntity<HttpResponseData> getSong(String surrogateKey) {

        Song song = songService.getSongBySurrogateKey(surrogateKey);
        SongDTO dto = asSongDTO(song);

        return httpResponse.returnResponse(HttpStatus.OK, SONG_CREATE_SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> saveSong(final HttpServletRequest request, final @RequestPart("payload") SongDTO requestPayload) {

        Song songPayload = asSong(requestPayload);
        Song savedSong = songService.saveSong(request, songPayload, requestPayload.getArtistSurrogateKeyList(), requestPayload.getGenreIdList(), null);
        SongDTO dto = asSongDTO(savedSong);

        return httpResponse.returnResponse(HttpStatus.OK, SONG_SAVE_SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> saveSong(final HttpServletRequest request, final @RequestPart("payload") SongDTO requestPayload, final @RequestPart("image") MultipartFile image) {

        Song songPayload = asSong(requestPayload);
        Song savedSong = songService.saveSong(request, songPayload, requestPayload.getArtistSurrogateKeyList(), requestPayload.getGenreIdList(), image);
        SongDTO dto = asSongDTO(savedSong);

        return httpResponse.returnResponse(HttpStatus.OK, SONG_SAVE_SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> createSong(final HttpServletRequest request, final @RequestPart("payload") SongDTO requestPayload) {
/* TODO: 12/30/2019  when adding a song, set imgurl to null. when ui displaying song image, display album's one. when user uploading a custom album art of the song
                     then remove the null of imgurl of the song and add the user uploaded one */

        Song songPayload = asSong(requestPayload);
        Song savedSong = songService.createSong(request, songPayload, requestPayload.getArtistSurrogateKeyList(), requestPayload.getGenreIdList(), null);
        SongDTO dto = asSongDTO(savedSong);

        return httpResponse.returnResponse(HttpStatus.OK, SONG_CREATE_SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> createSong(final HttpServletRequest request, final @RequestPart("payload") SongDTO requestPayload, final @RequestPart("image") MultipartFile image) {
/* TODO: 12/30/2019  when adding a song, set imgurl to null. when ui displaying song image, display album's one. when user uploading a custom album art of the song
                     then remove the null of imgurl of the song and add the user uploaded one
                      08/05/2021 I can't remember why I did the above.*/

        Song songPayload = asSong(requestPayload);
        Song savedSong = songService.createSong(request, songPayload, requestPayload.getArtistSurrogateKeyList(), requestPayload.getGenreIdList(), image);
        SongDTO dto = asSongDTO(savedSong);

        return httpResponse.returnResponse(HttpStatus.OK, SONG_CREATE_SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateSong(final HttpServletRequest request, final @RequestPart("payload") SongDTO requestPayload) {

        Song songPayload = asSong(requestPayload);
        Song savedSong = songService.updateSong(request, songPayload, requestPayload.getArtistSurrogateKeyList(), requestPayload.getGenreIdList(), null);
        SongDTO dto = asSongDTO(savedSong);

        return httpResponse.returnResponse(HttpStatus.OK, SONG_UPDATE_SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateSong(final HttpServletRequest request, final @RequestPart("payload") SongDTO requestPayload, final @RequestPart("image") MultipartFile image) {

        Song songPayload = asSong(requestPayload);
        Song savedSong = songService.updateSong(request, songPayload, requestPayload.getArtistSurrogateKeyList(), requestPayload.getGenreIdList(), image);
        SongDTO dto = asSongDTO(savedSong);

        return httpResponse.returnResponse(HttpStatus.OK, SONG_ALBUM_ART_UPDATE_SUCCESS.getSuccessMessage(), null, dto);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbumArt(final HttpServletRequest request, final @RequestBody Song payload) {

        //        todo
        //         songService.removeAlbumArt(request, songId);

        return httpResponse.returnResponse(HttpStatus.OK, SONG_ALBUM_ART_REMOVE_SUCCESS.getSuccessMessage(), null, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeSong(final HttpServletRequest request, final @RequestBody Song payload) {

        //        todo
        //        songService.removeSong(request, songId);

        return httpResponse.returnResponse(HttpStatus.OK, SONG_REMOVE_SUCCESS.getSuccessMessage(), null, null);
    }

    private Song asSong(final SongDTO requestDTO) {
        return Optional.ofNullable(conversionService.convert(requestDTO, Song.class)).orElseThrow(() -> new EntityConversionException(LYRICX_ERR_35));
    }

    private SongDTO asSongDTO(final Song song) {
        return Optional.ofNullable(conversionService.convert(song, SongDTO.class)).orElseThrow(() -> new EntityConversionException(LYRICX_ERR_36));
    }
}
