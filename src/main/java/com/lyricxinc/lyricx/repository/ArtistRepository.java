package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, UUID> {

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
     * @param artistUrl url of the artist
     * @return the corresponding artist
     */
    Artist findByArtistUrl(String artistUrl);

}
