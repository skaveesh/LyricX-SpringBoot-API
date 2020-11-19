package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lyricxinc.lyricx.model.socket.outbound.ArtistSuggestedItem;
import com.lyricxinc.lyricx.model.validator.group.OnAlbumCreate;
import com.lyricxinc.lyricx.model.validator.group.OnArtistCreate;
import com.lyricxinc.lyricx.model.validator.group.OnArtistUpdate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * The type Artist.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@SqlResultSetMapping(name = "artistSuggestionUsingArtistNameMapping", classes = {@ConstructorResult(targetClass = ArtistSuggestedItem.class, columns = {@ColumnResult(name = "surrogate_key"), @ColumnResult(name = "name")})})
@NamedNativeQuery(name = "Artist.findArtistSuggestionUsingArtistName", query = "SELECT a.surrogate_key, a.name FROM artist a WHERE a.name ILIKE CONCAT('%', :name, '%') ORDER BY a.name LIMIT 50", resultSetMapping = "artistSuggestionUsingArtistNameMapping")
public class Artist {

    @Id
    @SequenceGenerator(name = "artist_id_seq", sequenceName = "artist_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_seq")
    @JsonIgnore
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    @NotBlank(groups = {OnAlbumCreate.class, OnArtistUpdate.class}, message = "surrogateKey should not be blank")
    private String surrogateKey;

    @Size(max = 50)
    @NotBlank(groups = OnArtistCreate.class, message = "Artist name should not be blank")
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imgUrl;

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

    private Boolean approvedStatus;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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

    @PrePersist
    private void onCreate() {

        setSurrogateKey(UUID.randomUUID().toString());
    }

    /**
     * Instantiates a new Artist.
     */
    public Artist() {

    }

    /**
     * Instantiates a new Artist.
     *
     * @param name           the name
     * @param imgUrl         the img url
     * @param addedBy        the added by
     * @param approvedStatus the approved status
     */
    public Artist(@NotBlank @Size(max = 50) String name, @NotBlank String imgUrl, Contributor addedBy, Boolean approvedStatus) {

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

    /**
     * Is approved status Boolean.
     *
     * @return the Boolean
     */
    public Boolean isApprovedStatus() {

        return approvedStatus;
    }

    /**
     * Sets approved status.
     *
     * @param approvedStatus the approved status
     */
    public void setApprovedStatus(Boolean approvedStatus) {

        this.approvedStatus = approvedStatus;
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
     * Gets artist genres.
     *
     * @return the artist genres
     */
    public Set<ArtistGenre> getArtistGenres() {

        return artistGenres;
    }

    /**
     * Sets artist genres.
     *
     * @param artistGenres the artist genres
     */
    public void setArtistGenres(Set<ArtistGenre> artistGenres) {

        this.artistGenres = artistGenres;
    }

    /**
     * Gets albums.
     *
     * @return the albums
     */
    public Set<Album> getAlbums() {

        return albums;
    }

    /**
     * Sets albums.
     *
     * @param albums the albums
     */
    public void setAlbums(Set<Album> albums) {

        this.albums = albums;
    }

    /**
     * Gets artist songs.
     *
     * @return the artist songs
     */
    public Set<ArtistSong> getArtistSongs() {

        return artistSongs;
    }

    /**
     * Sets artist songs.
     *
     * @param artistSongs the artist songs
     */
    public void setArtistSongs(Set<ArtistSong> artistSongs) {

        this.artistSongs = artistSongs;
    }

}
