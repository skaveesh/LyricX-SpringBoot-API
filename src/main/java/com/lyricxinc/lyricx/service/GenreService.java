package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Genre;
import com.lyricxinc.lyricx.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {

        this.genreRepository = genreRepository;
    }

    public Genre findGenreByName(String genreName) {

        return genreRepository.findByGenreName(genreName);
    }

    public Genre getGenreById(short id) {

        return genreRepository.findById(id).orElse(null);
    }

    public void addGenre(String genreName) {

        genreRepository.save(new Genre(genreName));
    }

}
