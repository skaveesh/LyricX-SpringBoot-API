package com.lyricxinc.lyricx.model;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    private Contributor addedBy;

    private boolean approvedStatus;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ArtistGenre> artistGenres;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Album> albums;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ArtistSong> artistSongs;
}
