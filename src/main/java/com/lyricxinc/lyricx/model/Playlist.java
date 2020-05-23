package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Playlist {

    @Id
    @SequenceGenerator(name = "playlist_id_seq", sequenceName = "playlist_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playlist_id_seq")
    @JsonIgnore
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "chanterId", nullable = false)
    @JsonBackReference(value = "playlistsReferenceChanter")
    private Chanter chanter;

    @NotBlank
    private String playlistName;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "playlistSongsReferenceSong")
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

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

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
