package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findBySurrogateKey(String surrogateKey);

    @Query(value = "SELECT img_url FROM song WHERE surrogate_key=:surrogateKey LIMIT 1", nativeQuery = true)
    Optional<String> findImgUrlUsingSurrogateKey(@Param("surrogateKey") String surrogateKey);
}
