package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumCreate;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumUpdate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@DynamicUpdate
@DynamicInsert
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;

    @NotBlank(groups = OnAlbumCreate.class)
    @Size(max = 50)
    private String name;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contributorId", nullable = false)
    @Valid
    @JsonBackReference(value = "referenceAddedBy")
    private Contributor addedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "lastModifiedById", nullable = true)
    @JsonBackReference(value = "referenceLastModifiedBy")
    private Contributor lastModifiedBy;

    public Contributor getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Contributor lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    private boolean approvedStatus;

    @Column(unique = true)
    private String surrogateKey;

    @CreationTimestamp
    private LocalDateTime addedDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "artistGenresReferenceArtist")
    private Set<ArtistGenre> artistGenres;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "albumsReferenceArtist")
    private Set<Album> albums;

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "artistSongsReferenceArtist")
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

    public LocalDateTime getLastModifiedDate() {

        return lastModifiedDate;
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
