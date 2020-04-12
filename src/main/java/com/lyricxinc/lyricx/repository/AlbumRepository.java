package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findTop20ByNameIgnoreCaseContainingOrderByNameAsc(String keyword);

    Optional<Album> findBySurrogateKey(String surrogateKey);

    @Query(value = "SELECT img_url FROM album WHERE surrogate_key=:surrogateKey LIMIT 1", nativeQuery = true)
    Optional<String> findImgUrlUsingSurrogateKey(@Param("surrogateKey") String surrogateKey);

}
