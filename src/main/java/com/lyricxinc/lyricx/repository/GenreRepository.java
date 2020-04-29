package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Short> {

    Genre findByGenreName(String genreName);

}
