package com.lyricxinc.lyricx.model;

import javax.persistence.*;

@Entity
public class ArtistGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "artistId", nullable = false)
    private Artist artist;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "genreId", nullable = false)
    private Genre genre;

    public ArtistGenre() {

    }

    public ArtistGenre(Artist artist, Genre genre) {

        this.artist = artist;
        this.genre = genre;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public Artist getArtist() {

        return artist;
    }

    public void setArtist(Artist artist) {

        this.artist = artist;
    }

    public Genre getGenre() {

        return genre;
    }

    public void setGenre(Genre genre) {

        this.genre = genre;
    }


}
