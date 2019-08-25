package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findTop20ByNameIgnoreCaseContainingOrderByNameAsc(String keyword);
}
