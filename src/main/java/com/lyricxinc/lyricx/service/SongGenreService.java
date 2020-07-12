package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.util.BatchInsert;
import com.lyricxinc.lyricx.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SongGenreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createSongGenre(Song song,  List<Genre> genreList) {

        new BatchInsert<Genre, Song, SongGenre>().process(song, genreList, entityManager, SongGenre::new);

    }
}
