package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ArtistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private LocalDateTime addedDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "artistId", nullable = false)
    private Artist artist;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "songId", nullable = false)
    private Song song;

    public ArtistSong() {

    }

    public ArtistSong(Artist artist, Song song) {

        this.artist = artist;
        this.song = song;
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

    public Song getSong() {

        return song;
    }

    public void setSong(Song song) {

        this.song = song;
    }

}
