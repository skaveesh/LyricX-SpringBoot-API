package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.repository.ArtistGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistGenreService {

    private final ArtistGenreRepository artistGenreRepository;
    private final ArtistService artistService;
    private final GenreService genreService;

    @Autowired
    public ArtistGenreService(ArtistGenreRepository artistGenreRepository, ArtistService artistService, GenreService genreService) {

        this.artistGenreRepository = artistGenreRepository;
        this.artistService = artistService;
        this.genreService = genreService;
    }

    public void addArtistGenre(long artistId, short genreId) {
        //ArtistGenre artistGenre = new ArtistGenre(artistService.getArtistById(artistId), genreService.getGenreById(genreId));
        artistGenreRepository.addArtistAndGenre(artistId, genreId);
    }

    public int removeArtistGenre(long artistId, short genreId) {

        return artistGenreRepository.deleteByArtist_IdAndGenre_Id(artistId, genreId);
    }

}
