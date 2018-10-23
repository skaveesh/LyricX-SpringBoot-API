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

    public Artist() {
    }

    public Artist(@NotBlank @Size(max = 50) String name, @NotBlank String imgUrl, Contributor addedBy, boolean approvedStatus, Set<ArtistGenre> artistGenres) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.addedBy = addedBy;
        this.approvedStatus = approvedStatus;
        this.artistGenres = artistGenres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Contributor getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Contributor addedBy) {
        this.addedBy = addedBy;
    }

    public boolean isApprovedStatus() {
        return approvedStatus;
    }

    public void setApprovedStatus(boolean approvedStatus) {
        this.approvedStatus = approvedStatus;
    }

    public Set<ArtistGenre> getArtistGenres() {
        return artistGenres;
    }

    public void setArtistGenres(Set<ArtistGenre> artistGenres) {
        this.artistGenres = artistGenres;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public Set<ArtistSong> getArtistSongs() {
        return artistSongs;
    }

    public void setArtistSongs(Set<ArtistSong> artistSongs) {
        this.artistSongs = artistSongs;
    }
}
