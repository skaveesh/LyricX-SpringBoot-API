package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SongGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "genreId", nullable = false)
    @JsonBackReference(value = "referenceGenre")
    private Genre genre;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "songId", nullable = false)
    @JsonBackReference(value = "songGenresReferenceSong")
    private Song song;

    public SongGenre() {

    }

    public SongGenre(Genre genre, Song song) {

        this.genre = genre;
        this.song = song;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Genre getGenre() {

        return genre;
    }

    public void setGenre(Genre genre) {

        this.genre = genre;
    }

    public Song getSong() {

        return song;
    }

    public void setSong(Song song) {

        this.song = song;
    }

}
