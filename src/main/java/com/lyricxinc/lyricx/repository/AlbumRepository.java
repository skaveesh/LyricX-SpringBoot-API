package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Album repository.
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    /**
     * Find top 20 by name ignore case containing order by name asc list.
     *
     * @param keyword the keyword
     * @return the list
     */
    List<Album> findTop20ByNameIgnoreCaseContainingOrderByNameAsc(String keyword);

    /**
     * Find by surrogate key optional.
     *
     * @param surrogateKey the surrogate key
     * @return the optional
     */
    Optional<Album> findBySurrogateKey(String surrogateKey);

    /**
     * Find img url using surrogate key optional.
     *
     * @param surrogateKey the surrogate key
     * @return the optional
     */
    @Query(value = "SELECT img_url FROM album WHERE surrogate_key=:surrogateKey LIMIT 1", nativeQuery = true)
    Optional<String> findImgUrlUsingSurrogateKey(@Param("surrogateKey") String surrogateKey);

}
