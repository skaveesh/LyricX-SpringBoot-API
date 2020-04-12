package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Artist repository.
 */
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    /**
     * find list of artists by name
     *
     * @param name of the artist
     * @return the corresponding artists list
     */
    List<Artist> findByName(String name);

    /**
     * find artist by artist url
     *
     * @param surrogateKey url of the artist
     * @return the corresponding artist
     */
    Optional<Artist> findBySurrogateKey(String surrogateKey);

    /**
     * Find img url using surrogate key optional.
     *
     * @param surrogateKey the surrogate key
     * @return the optional
     */
    @Query(value = "SELECT img_url FROM artist WHERE surrogate_key=:surrogateKey LIMIT 1", nativeQuery = true)
    Optional<String> findImgUrlUsingSurrogateKey(@Param("surrogateKey") String surrogateKey);

}
