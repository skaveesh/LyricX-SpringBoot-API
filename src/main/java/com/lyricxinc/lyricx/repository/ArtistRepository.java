package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.socket.outbound.ArtistSuggestedItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    /**
     * Find by surrogate key in list.
     *
     * @param surrogateKeySet the surrogate key set
     * @return the list
     */
    List<Artist> findBySurrogateKeyIn(Set<String> surrogateKeySet);

    /**
     * Find artist suggestion using artist name list.
     *
     * @param name the name
     * @return the list
     */
    @Query(nativeQuery = true)
    List<ArtistSuggestedItem> findArtistSuggestionUsingArtistName(@Param("name") String name);

    /**
     * Find by name containing ignore case.
     *
     * @param name     the name
     * @param pageable the pageable
     * @return the optional
     */
    Page<Artist> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
