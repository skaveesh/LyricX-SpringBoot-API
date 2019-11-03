package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ArtistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @CreationTimestamp
    private LocalDateTime addedDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "artistId", nullable = false)
    @JsonBackReference(value = "artistSongsReferenceArtist")
    private Artist artist;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "songId", nullable = false)
    @JsonBackReference(value = "artistSongsReferenceSong")
    private Song song;

    public ArtistSong() {

    }

    public ArtistSong(Artist artist, Song song) {

        this.artist = artist;
        this.song = song;
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

    public Song getSong() {

        return song;
    }

    public void setSong(Song song) {

        this.song = song;
    }

}
