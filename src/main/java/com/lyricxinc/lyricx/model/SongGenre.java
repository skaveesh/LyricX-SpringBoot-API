package com.lyricxinc.lyricx.model;

import javax.persistence.*;

@Entity
public class SongGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private Song song;

    public SongGenre() {
    }

    public SongGenre(Genre genre, Song song) {
        this.genre = genre;
        this.song = song;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
