package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "playlistId", nullable = false)
    @JsonBackReference(value = "referencePlaylist")
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "songId", nullable = false)
    @JsonBackReference(value = "playlistSongsReferenceSong")
    private Song song;

    public PlaylistSong() {

    }

    public PlaylistSong(Playlist playlist, Song song) {

        this.playlist = playlist;
        this.song = song;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Playlist getPlaylist() {

        return playlist;
    }

    public void setPlaylist(Playlist playlist) {

        this.playlist = playlist;
    }

    public Song getSong() {

        return song;
    }

    public void setSong(Song song) {

        this.song = song;
    }

}
