package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.util.BatchInsert;
import com.lyricxinc.lyricx.model.Genre;
import com.lyricxinc.lyricx.model.Song;
import com.lyricxinc.lyricx.model.SongGenre;
import com.lyricxinc.lyricx.repository.SongGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SongGenreService {

    private final SongGenreRepository songGenreRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public SongGenreService(SongGenreRepository songGenreRepository) {

        this.songGenreRepository = songGenreRepository;
    }

    @Transactional
    public void createSongGenre(Song song, List<Genre> genreList) {

        new BatchInsert<Genre, Song, SongGenre>().process(song, genreList, entityManager, SongGenre::new);
    }

    @Transactional
    public void deleteSongGenreInBatch(Set<SongGenre> songGenreList) {

        songGenreRepository.deleteInBatch(songGenreList);
    }
}
