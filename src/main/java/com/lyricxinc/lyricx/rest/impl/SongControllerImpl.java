package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.rest.controller.SongController;
import com.lyricxinc.lyricx.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<HttpResponseData> addSong(HttpServletRequest request, String name, long albumId, String guitarKey, String beat, short languageId, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink) {

        songService.addSong(request, name, albumId, guitarKey, beat, languageId, keywords, lyrics, youTubeLink, spotifyLink, deezerLink);

        return httpResponse.returnResponse(HttpStatus.OK, "Song added successfully.", null);
    }

    @Override
    public ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, long songId, String name, long albumId, String guitarKey, String beat, short languageId, String keywords, byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink) {
        //TODO
        return null;
    }

    @Override
    public ResponseEntity<HttpResponseData> updateSong(HttpServletRequest request, long songId, MultipartFile image) {
        //TODO
        return null;
    }

    @Override
    public ResponseEntity<HttpResponseData> removeAlbumArt(HttpServletRequest request, long songId) {
        //TODO
        return null;
    }

    @Override
    public ResponseEntity<HttpResponseData> removeSong(HttpServletRequest request, long songId) {
        //TODO
        return null;
    }
}
