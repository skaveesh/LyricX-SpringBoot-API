package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ArtistGenre {

    @Id
    @SequenceGenerator(name = "artist_genre_id_seq", sequenceName = "artist_genre_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_genre_id_seq")
    @JsonIgnore
    private Long id;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    public ArtistGenre(Genre genre, Artist artist) {

        this.genre = genre;
        this.artist = artist;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

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
