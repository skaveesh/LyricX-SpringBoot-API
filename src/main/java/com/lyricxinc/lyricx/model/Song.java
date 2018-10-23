package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private Album album;

    @Size(max = 5)
    private String guitarKey;

    @Size(max = 5)
    private String beat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private Language language;

    private String keywords;

    @NotBlank
    private String lyrics;

    private String youTubeLink;

    private String spotifyLink;

    private String deezerLink;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    private Contributor addedBy;

    @CreationTimestamp
    private LocalDateTime addedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    private Contributor lastModifiedBy;

    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    private Contributor publishedBy;

    private LocalDateTime publishedDate;

    private boolean publishedState;

    @OneToMany(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SongGenre> songGenres;

    @OneToMany(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ArtistSong> artistSongs;

    @OneToMany(mappedBy = "song", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SongModify> songModifies;

    
}
