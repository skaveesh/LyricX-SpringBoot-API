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
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "artistId", nullable = false)
    @Valid
    @JsonBackReference(value = "albumsReferenceArtist")
    private Artist artist;

    @PastOrPresent(groups = OnAlbumCreate.class)
    private Year year;

    @NotBlank(groups = OnAlbumCreate.class)
    private String name;

    private String imgUrl;

    @Column(unique = true)
    @NotNull(groups = OnAlbumUpdate.class)
    private String surrogateKey;

    @CreationTimestamp
    private LocalDateTime addedDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contributorId", nullable = false)
    @Valid
    @JsonBackReference(value = "referenceAddedBy")
    private Contributor addedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lastModifiedById")
    @Valid
    @JsonBackReference(value = "referenceLastModifiedBy")
    private Contributor lastModifiedBy;

    private boolean approvedStatus;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "songReferenceAlbum")
    private Set<Song> songs;

    public Album() {

    }

    public Album(Artist artist, Year year, @NotBlank String name, @NotBlank String imgUrl, Contributor addedBy, boolean approvedStatus) {

        this.artist = artist;
        this.year = year;
        this.name = name;
        this.imgUrl = imgUrl;
        this.addedBy = addedBy;
        this.approvedStatus = approvedStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {

        return artist;
    }

    public void setArtist(Artist artist) {

        this.artist = artist;
    }

    public Year getYear() {

        return year;
    }

    public void setYear(Year year) {

        this.year = year;
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

    public Set<Song> getSongs() {

        return songs;
    }

    public void setSongs(Set<Song> songs) {

        this.songs = songs;
    }

    public Contributor getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Contributor lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
