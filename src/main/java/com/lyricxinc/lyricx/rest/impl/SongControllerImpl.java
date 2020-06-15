package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.dto.SongCreateUpdateRequest;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.lyricxinc.lyricx.core.constant.Constants.SuccessCode;
import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage;

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
    public ResponseEntity<HttpResponseData> createSong(HttpServletRequest request, final @RequestBody SongCreateUpdateRequest payload) {
/* TODO: 12/30/2019  when adding a song, set imgurl to null. when ui displaying song image, display album's one. when user uploading a custom album art of the song
                     then remove the null of imgurl of the song and add the user uploaded one */

        songService.createSong(request, conversionService.convert(payload, Song.class));

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_CREATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, final @RequestBody Song payload) {

        //        songService.updateSong(request, songId, name, albumId, guitarKey, beat, languageId, keywords, lyrics, youTubeLink, spotifyLink, deezerLink);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_UPDATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, final @RequestBody Song payload, MultipartFile image) {

        //        songService.updateSong(request, songId, image);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_ALBUM_ART_UPDATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, final @RequestBody Song payload) {

        //        todo
        //         songService.removeAlbumArt(request, songId);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_ALBUM_ART_REMOVE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeSong(HttpServletRequest request, final @RequestBody Song payload) {

        //        todo
        //        songService.removeSong(request, songId);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_REMOVE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

}
