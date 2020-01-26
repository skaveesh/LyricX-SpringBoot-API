package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.rest.controller.SongController;
import com.lyricxinc.lyricx.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.lyricxinc.lyricx.core.constant.Constants.SuccessCode;
import static com.lyricxinc.lyricx.core.constant.Constants.SuccessMessage;

@RestController
public class SongControllerImpl implements SongController {

    private final SongService songService;
    private final HttpResponse httpResponse;

    @Autowired
    public SongControllerImpl(HttpResponse httpResponse, SongService songService) {

        this.httpResponse = httpResponse;
        this.songService = songService;
    }

    @Override
    public ResponseEntity<HttpResponseData> addSong(final HttpServletRequest request, final @Valid @RequestBody Song payload) {
/* TODO: 12/30/2019  when adding a song, set imgurl to null. when ui displaying song image, display album's one. when user uploading a custom album art of the song
                     then remove the null of imgurl of the song and add the user uploaded one */

        songService.addSong(request, payload);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_CREATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, final @Valid @RequestBody Song payload) {

//        songService.updateSong(request, songId, name, albumId, guitarKey, beat, languageId, keywords, lyrics, youTubeLink, spotifyLink, deezerLink);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_UPDATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, final @Valid @RequestBody Song payload, MultipartFile image) {

//        songService.updateSong(request, songId, image);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_ALBUM_ART_UPDATE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, final @Valid @RequestBody Song payload) {

        //        todo
        //         songService.removeAlbumArt(request, songId);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_ALBUM_ART_REMOVE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

    @Override
    public ResponseEntity<HttpResponseData> removeSong(HttpServletRequest request, final @Valid @RequestBody Song payload) {

//        todo
        //        songService.removeSong(request, songId);

        return httpResponse.returnResponse(HttpStatus.OK, SuccessMessage.SONG_REMOVE_SUCCESS, SuccessCode.LYRICX_SUC_00, null);
    }

}
