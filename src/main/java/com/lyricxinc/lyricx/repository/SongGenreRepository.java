package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.SongGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongGenreRepository extends JpaRepository<SongGenre, Long> {}
