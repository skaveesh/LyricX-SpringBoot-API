package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Genre;
import com.lyricxinc.lyricx.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * The type Genre service.
 */
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    /**
     * Instantiates a new Genre service.
     *
     * @param genreRepository the genre repository
     */
    @Autowired
    public GenreService(GenreRepository genreRepository) {

        this.genreRepository = genreRepository;
    }

    /**
     * Find genre by name genre.
     *
     * @param genreName the genre name
     * @return the genre
     */
    public Genre findGenreByName(String genreName) {

        return genreRepository.findByGenreName(genreName);
    }

    /**
     * Gets genre by id.
     *
     * @param id the id
     * @return the genre by id
     */
    public Genre getGenreById(Short id) {

        return genreRepository.findById(id).orElse(null);
    }

    /**
     * Add genre.
     *
     * @param genreName the genre name
     */
    public void addGenre(String genreName) {

        genreRepository.save(new Genre(genreName));
    }

    /**
     * Gets all genres.
     *
     * @return the all genres
     */
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    /**
     * Find genre by ids list.
     *
     * @param genreIdSet the genre id set
     * @return the list
     */
    public List<Genre> findGenreByIds(Set<Short> genreIdSet){
        return genreRepository.findByIdIn(genreIdSet);
    }

}
