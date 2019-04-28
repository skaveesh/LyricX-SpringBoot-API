package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Set;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "artistId", nullable = false)
    private Artist artist;

    private Year year;

    @NotBlank
    private String name;

    @NotBlank
    private String imgUrl;

    @Column(unique = true)
    private String albumUrl;

    @CreationTimestamp
    private LocalDateTime addedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contributorId", nullable = false)
    private Contributor addedBy;

    private boolean approvedStatus;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Song> songs;

    public Album() {
    }

    public Album(Artist artist, Year year, @NotBlank String name, @NotBlank String imgUrl, Contributor addedBy, boolean approvedStatus, String albumUrl) {
        this.artist = artist;
        this.year = year;
        this.name = name;
        this.imgUrl = imgUrl;
        this.addedBy = addedBy;
        this.approvedStatus = approvedStatus;
        this.albumUrl = albumUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
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
}
