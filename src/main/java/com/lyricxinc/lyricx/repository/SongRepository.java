package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Album;
import com.lyricxinc.lyricx.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Song repository.
 */
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    /**
     * Find by surrogate key optional.
     *
     * @param surrogateKey the surrogate key
     * @return the optional
     */
    Optional<Song> findBySurrogateKey(String surrogateKey);

    /**
     * Find by album optional.
     *
     * @param album the album
     * @return the optional
     */
    Optional<List<Song>> findByAlbum(Album album);

    /**
     * Find img url using id optional.
     *
     * @param id the id
     * @return the optional
     */
    @Query(value = "SELECT img_url FROM song WHERE id=:id LIMIT 1", nativeQuery = true)
    Optional<String> findImgUrlUsingId(@Param("id") Long id);

    /**
     * Find img url using surrogate key optional.
     *
     * @param surrogateKey the surrogate key
     * @return the optional
     */
    @Query(value = "SELECT img_url FROM song WHERE surrogate_key=:surrogateKey LIMIT 1", nativeQuery = true)
    Optional<String> findImgUrlUsingSurrogateKey(@Param("surrogateKey") String surrogateKey);
}
