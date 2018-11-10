package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist getArtistById(Long id){
        return artistRepository.findById(id).orElse(null);
    }

    public void addArtist(Artist artist) {
        artist.setArtistUrl( UUID.randomUUID().toString().replace("-", ""));
        artistRepository.save(artist);
    }

    public void updateArtist(Artist artist){
        artistRepository.save(artist);
    }

    public void removeArtist(long id){
        artistRepository.deleteById(id);
    }
}
