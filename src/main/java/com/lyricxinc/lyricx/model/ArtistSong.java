package com.lyricxinc.lyricx.model;

import javax.persistence.*;

@Entity
public class ArtistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private Artist artist;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
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
