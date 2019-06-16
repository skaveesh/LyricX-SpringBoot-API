package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

}
