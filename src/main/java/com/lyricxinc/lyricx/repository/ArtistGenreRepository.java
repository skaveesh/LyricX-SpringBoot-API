package com.lyricxinc.lyricx.repository;

import com.lyricxinc.lyricx.model.ArtistGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ArtistGenreRepository extends JpaRepository<ArtistGenre, Long> {

    @Modifying
    @Query(value = "insert into artist_genre (artist_id, genre_id) values(:artist_id, :genre_id)", nativeQuery = true)
    @Transactional
    int addArtistAndGenre(@Param("artist_id") Long artistId, @Param("genre_id") Short genreId);

    @Transactional
    int deleteByArtist_IdAndGenre_Id(long artistId, short genreId);

    ArtistGenre findByArtist_IdAndGenre_Id(long artistId, short genreId);

}
