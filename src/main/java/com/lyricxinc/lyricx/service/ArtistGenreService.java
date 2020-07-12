package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.util.BatchInsert;
import com.lyricxinc.lyricx.model.Artist;
import com.lyricxinc.lyricx.model.ArtistGenre;
import com.lyricxinc.lyricx.model.Genre;
import com.lyricxinc.lyricx.repository.ArtistGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * The type Artist genre service.
 */
@Service
public class ArtistGenreService {

    private final ArtistGenreRepository artistGenreRepository;
    private final ArtistService artistService;
    private final GenreService genreService;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Instantiates a new Artist genre service.
     *
     * @param artistGenreRepository the artist genre repository
     * @param artistService         the artist service
     * @param genreService          the genre service
     */
    @Autowired
    public ArtistGenreService(ArtistGenreRepository artistGenreRepository, ArtistService artistService, GenreService genreService) {

        this.artistGenreRepository = artistGenreRepository;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    /**
     * Add artist genre.
     *
     * @param artistId the artist id
     * @param genreId  the genre id
     */
    public void addArtistGenre(Long artistId, Short genreId) {

        artistGenreRepository.addArtistAndGenre(artistId, genreId);
    }

    /**
     * Remove artist genre int.
     *
     * @param artistId the artist id
     * @param genreId  the genre id
     * @return the int
     */
    public int removeArtistGenre(Long artistId, Short genreId) {

        return artistGenreRepository.deleteByArtist_IdAndGenre_Id(artistId, genreId);
    }

    /**
     * Create artist genre.
     *
     * @param artist    the artist
     * @param genreList the genre list
     */
    @Transactional
    public void createArtistGenre(Artist artist, List<Genre> genreList) {

        new BatchInsert<Genre, Artist, ArtistGenre>().process(artist, genreList, entityManager, ArtistGenre::new);

    }

}
