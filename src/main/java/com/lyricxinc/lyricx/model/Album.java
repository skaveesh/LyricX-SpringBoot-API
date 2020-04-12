package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumCreate;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumUpdate;
import com.lyricxinc.lyricx.model.validator.group.OnSongCreate;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Set;
import java.util.UUID;

@Entity
@DynamicInsert
@DynamicUpdate
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    @NotBlank(groups = {OnAlbumUpdate.class, OnSongCreate.class})
    private String surrogateKey;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "artistId", nullable = false)
    @Valid
    @JsonBackReference(value = "albumsReferenceArtist")
    @NotNull(groups = {OnAlbumCreate.class})
    private Artist artist;

    @PastOrPresent(groups = OnAlbumCreate.class)
    private Year year;

    @Size(max = 50)
    @NotBlank(groups = OnAlbumCreate.class)
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imgUrl;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModifiedDate;

    //todo getting contributor through session?
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contributorId", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference(value = "referenceAddedBy")
    private Contributor addedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lastModifiedById")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference(value = "referenceLastModifiedBy")
    private Contributor lastModifiedBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean approvedStatus;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonManagedReference(value = "songReferenceAlbum")
    private Set<Song> songs;

    @PrePersist
    private void onCreate(){
        setSurrogateKey(UUID.randomUUID().toString());
    }

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

    public String getSurrogateKey() {

        return surrogateKey;
    }

    public void setSurrogateKey(String surrogateKey) {

        this.surrogateKey = surrogateKey;
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
