package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.util.BatchInsert;
import com.lyricxinc.lyricx.model.*;
import com.lyricxinc.lyricx.repository.ArtistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ArtistSongService {

    private final ArtistSongRepository artistSongRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ArtistSongService(ArtistSongRepository artistSongRepository) {

        this.artistSongRepository = artistSongRepository;
    }

    public void createArtistSong(Artist artist, Song song) {

        ArtistSong artistSong = new ArtistSong(artist, song);
    }

    @Transactional
    public void createArtistSong(Song song, List<Artist> artistList) {

        new BatchInsert<Artist, Song, ArtistSong>().process(song, artistList, entityManager, ArtistSong::new);

    }

}
