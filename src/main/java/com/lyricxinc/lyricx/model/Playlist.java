package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "chanterId", nullable = false)
    private Chanter chanter;

    @NotBlank
    private String playlistName;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<PlaylistSong> playlistSongs;

    public Playlist() {

    }

    public Playlist(Chanter chanter, @NotBlank String playlistName) {

        this.chanter = chanter;
        this.playlistName = playlistName;
    }

    public Playlist(Chanter chanter, @NotBlank String playlistName, Set<PlaylistSong> playlistSongs) {

        this.chanter = chanter;
        this.playlistName = playlistName;
        this.playlistSongs = playlistSongs;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public Chanter getChanter() {

        return chanter;
    }

    public void setChanter(Chanter chanter) {

        this.chanter = chanter;
    }

    public String getPlaylistName() {

        return playlistName;
    }

    public void setPlaylistName(String playlistName) {

        this.playlistName = playlistName;
    }

    public LocalDateTime getCreatedDate() {

        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {

        return lastModifiedDate;
    }

    public Set<PlaylistSong> getPlaylistSongs() {

        return playlistSongs;
    }

    public void setPlaylistSongs(Set<PlaylistSong> playlistSongs) {

        this.playlistSongs = playlistSongs;
    }

}
