package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

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
    @JoinColumn(name = "id")
    private AppUser appUser;

    @NotBlank
    private String playlistName;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PlaylistSong> playlistSongs;
}
