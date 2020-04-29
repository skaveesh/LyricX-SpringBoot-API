package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lyricxinc.lyricx.model.socket.outbound.AlbumSuggestedItem;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumCreate;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumUpdate;
import com.lyricxinc.lyricx.model.validator.group.OnSongCreate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Set;
import java.util.UUID;

/**
 * The type Album.
 */
@Entity
@DynamicInsert
@DynamicUpdate
@SqlResultSetMapping(name = "albumSuggestionUsingAlbumNameMapping", classes = {@ConstructorResult(targetClass = AlbumSuggestedItem.class, columns = {@ColumnResult(name = "surrogate_key"), @ColumnResult(name = "name")})})
@NamedNativeQuery(name = "Album.findAlbumSuggestionUsingAlbumName", query = "SELECT a.surrogate_key, a.name FROM album a WHERE a.name LIKE CONCAT('%', :name, '%') ORDER BY a.name LIMIT 2", resultSetMapping = "albumSuggestionUsingAlbumNameMapping")
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
    private void onCreate() {

        setSurrogateKey(UUID.randomUUID().toString());
    }

    /**
     * Instantiates a new Album.
     */
    public Album() {

    }

    /**
     * Instantiates a new Album.
     *
     * @param artist         the artist
     * @param year           the year
     * @param name           the name
     * @param imgUrl         the img url
     * @param addedBy        the added by
     * @param approvedStatus the approved status
     */
    public Album(Artist artist, Year year, @NotBlank String name, @NotBlank String imgUrl, Contributor addedBy, boolean approvedStatus) {

        this.artist = artist;
        this.year = year;
        this.name = name;
        this.imgUrl = imgUrl;
        this.addedBy = addedBy;
        this.approvedStatus = approvedStatus;

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {

        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * Gets surrogate key.
     *
     * @return the surrogate key
     */
    public String getSurrogateKey() {

        return surrogateKey;
    }

    /**
     * Sets surrogate key.
     *
     * @param surrogateKey the surrogate key
     */
    public void setSurrogateKey(String surrogateKey) {

        this.surrogateKey = surrogateKey;
    }

    /**
     * Gets artist.
     *
     * @return the artist
     */
    public Artist getArtist() {

        return artist;
    }

    /**
     * Sets artist.
     *
     * @param artist the artist
     */
    public void setArtist(Artist artist) {

        this.artist = artist;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public Year getYear() {

        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(Year year) {

        this.year = year;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {

        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Gets img url.
     *
     * @return the img url
     */
    public String getImgUrl() {

        return imgUrl;
    }

    /**
     * Sets img url.
     *
     * @param imgUrl the img url
     */
    public void setImgUrl(String imgUrl) {

        this.imgUrl = imgUrl;
    }

    /**
     * Gets added date.
     *
     * @return the added date
     */
    public LocalDateTime getAddedDate() {

        return addedDate;
    }

    /**
     * Gets last modified date.
     *
     * @return the last modified date
     */
    public LocalDateTime getLastModifiedDate() {

        return lastModifiedDate;
    }

    /**
     * Gets added by.
     *
     * @return the added by
     */
    public Contributor getAddedBy() {

        return addedBy;
    }

    /**
     * Sets added by.
     *
     * @param addedBy the added by
     */
    public void setAddedBy(Contributor addedBy) {

        this.addedBy = addedBy;
    }

    /**
     * Is approved status boolean.
     *
     * @return the boolean
     */
    public boolean isApprovedStatus() {

        return approvedStatus;
    }

    /**
     * Sets approved status.
     *
     * @param approvedStatus the approved status
     */
    public void setApprovedStatus(boolean approvedStatus) {

        this.approvedStatus = approvedStatus;
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public Set<Song> getSongs() {

        return songs;
    }

    /**
     * Sets songs.
     *
     * @param songs the songs
     */
    public void setSongs(Set<Song> songs) {

        this.songs = songs;
    }

    /**
     * Gets last modified by.
     *
     * @return the last modified by
     */
    public Contributor getLastModifiedBy() {

        return lastModifiedBy;
    }

    /**
     * Sets last modified by.
     *
     * @param lastModifiedBy the last modified by
     */
    public void setLastModifiedBy(Contributor lastModifiedBy) {

        this.lastModifiedBy = lastModifiedBy;
    }

}
