package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.ArtistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistSongRepository extends JpaRepository<ArtistSong, Long> {}
