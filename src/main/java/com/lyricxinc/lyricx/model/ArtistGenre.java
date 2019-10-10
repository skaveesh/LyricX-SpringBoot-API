package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ArtistGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @CreationTimestamp
    private LocalDateTime addedDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "artistId", nullable = false)
    @JsonBackReference(value = "artistGenresReferenceArtist")
    private Artist artist;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "genreId", nullable = false)
    @JsonBackReference(value = "referenceGenre")
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

    public LocalDateTime getAddedDate() {

        return addedDate;
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
