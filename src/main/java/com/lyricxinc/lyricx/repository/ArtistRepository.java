package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    Artist findBySurrogateKey(String surrogateKey);

}
