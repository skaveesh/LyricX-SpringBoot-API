package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * The interface Genre repository.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Short> {

    /**
     * Find by genre name genre.
     *
     * @param genreName the genre name
     * @return the genre
     */
    Genre findByGenreName(String genreName);

    /**
     * Find by id in list.
     *
     * @param genreIdSet the genre id set
     * @return the list
     */
    List<Genre> findByIdIn(Set<Short> genreIdSet);

}
