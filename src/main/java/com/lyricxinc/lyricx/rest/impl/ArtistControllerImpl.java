package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.Contributor;
import com.lyricxinc.lyricx.rest.controller.ArtistController;
import com.lyricxinc.lyricx.service.AmazonClientService;
import com.lyricxinc.lyricx.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ArtistControllerImpl implements ArtistController {

    private final ArtistService artistService;

    @Autowired
    ArtistControllerImpl( ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public void addArtist(HttpServletRequest request, String name, MultipartFile image) {
        artistService.addArtist(request, name, image);
    }

    @Override
    public void updateArtistName(HttpServletRequest request, long artistId, String name) {
        Artist artist = artistService.getArtistById(artistId);
        artistService.updateArtist(request, artist, name);
    }

    @Override
    public void updateArtistImage(HttpServletRequest request, long artistId, MultipartFile image) {
        Artist artist = artistService.getArtistById(artistId);
        artistService.updateArtist(request, artist, image);
    }
}
