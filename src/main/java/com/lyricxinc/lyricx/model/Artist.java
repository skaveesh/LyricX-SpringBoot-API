package com.lyricxinc.lyricx.model;

import com.lyricxinc.lyricx.model.validator.group.OnAlbumCreate;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumUpdate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {OnAlbumCreate.class, OnAlbumUpdate.class})
    private long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contributorId", nullable = false)
    private Contributor addedBy;

    private boolean approvedStatus;

    @NotNull
    @Column(unique = true)
    private String surrogateKey;

    @CreationTimestamp
    private LocalDateTime addedDate;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ArtistGenre> artistGenres;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Album> albums;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<ArtistSong> artistSongs;

    public Artist() {

    }

    public Artist(@NotBlank @Size(max = 50) String name, @NotBlank String imgUrl, Contributor addedBy, boolean approvedStatus) {

        this.name = name;
        this.imgUrl = imgUrl;
        this.addedBy = addedBy;
        this.approvedStatus = approvedStatus;
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

    public String getSurrogateKey() {

        return surrogateKey;
    }

    public void setSurrogateKey(String surrogateKey) {

        this.surrogateKey = surrogateKey;
    }

    public LocalDateTime getAddedDate() {

        return addedDate;
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
