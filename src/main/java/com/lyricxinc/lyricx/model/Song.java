package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lyricxinc.lyricx.model.validator.group.OnSongCreate;
import com.lyricxinc.lyricx.model.validator.group.OnSongUpdate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@DynamicInsert
@DynamicUpdate
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    @NotNull(groups = OnSongUpdate.class)
    private String surrogateKey;

    @NotBlank(groups = {OnSongCreate.class})
    private String name;

    //todo valid here?
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "albumId", nullable = false)
    @JsonBackReference(value = "songReferenceAlbum")
    private Album album;

    @Size(max = 5)
    private String guitarKey;

    @Size(max = 5)
    private String beat;

    //todo valid here
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "languageId", nullable = false)
    @JsonBackReference(value = "referenceLanguage")
    private Language language;

    private String keywords;

    @Lob
    @NotNull
    @Column(length = 100000)
    private byte[] lyrics;

    private String youTubeLink;

    private String spotifyLink;

    private String deezerLink;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imgUrl;

    private boolean isExplicit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "addedById", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference(value = "referenceAddedBy")
    private Contributor addedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "lastModifiedById", nullable = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference(value = "referenceLastModifiedBy")
    private Contributor lastModifiedBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "publishedById", nullable = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonBackReference(value = "referencePublishedBy")
    private Contributor publishedBy;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModifiedDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime publishedDate;

    //todo @JsonProperty(access = JsonProperty.Access.READ_ONLY) this is commented because contributor can make this true. but check before update if the contributor is senior
    private boolean publishedState;

    @OneToMany(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "songGenresReferenceSong")
    private Set<SongGenre> songGenres;

    @OneToMany(mappedBy = "song", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "artistSongsReferenceSong")
    private Set<ArtistSong> artistSongs;

    @OneToMany(mappedBy = "song", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "songModifiesReferenceSong")
    private Set<SongModify> songModifies;

    @PrePersist
    private void onCreate(){
        setSurrogateKey(UUID.randomUUID().toString());
    }

    public Song() {

    }

    public Song(@NotBlank String name, Album album, @Size(max = 5) String guitarKey, @Size(max = 5) String beat, Language language, String keywords, @NotBlank byte[] lyrics, String youTubeLink, String spotifyLink, String deezerLink, String imgUrl, boolean isExplicit, Contributor addedBy, boolean publishedState) {

        this.name = name;
        this.album = album;
        this.guitarKey = guitarKey;
        this.beat = beat;
        this.language = language;
        this.keywords = keywords;
        this.lyrics = lyrics;
        this.youTubeLink = youTubeLink;
        this.spotifyLink = spotifyLink;
        this.deezerLink = deezerLink;
        this.imgUrl = imgUrl;
        this.isExplicit = isExplicit;
        this.addedBy = addedBy;
        this.publishedState = publishedState;
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

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Album getAlbum() {

        return album;
    }

    public void setAlbum(Album album) {

        this.album = album;
    }

    public String getGuitarKey() {

        return guitarKey;
    }

    public void setGuitarKey(String guitarKey) {

        this.guitarKey = guitarKey;
    }

    public String getBeat() {

        return beat;
    }

    public void setBeat(String beat) {

        this.beat = beat;
    }

    public Language getLanguage() {

        return language;
    }

    public void setLanguage(Language language) {

        this.language = language;
    }

    public String getKeywords() {

        return keywords;
    }

    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    public byte[] getLyrics() {

        return lyrics;
    }

    public void setLyrics(byte[] lyrics) {

        this.lyrics = lyrics;
    }

    public String getYouTubeLink() {

        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {

        this.youTubeLink = youTubeLink;
    }

    public String getSpotifyLink() {

        return spotifyLink;
    }

    public void setSpotifyLink(String spotifyLink) {

        this.spotifyLink = spotifyLink;
    }

    public String getDeezerLink() {

        return deezerLink;
    }

    public void setDeezerLink(String deezerLink) {

        this.deezerLink = deezerLink;
    }

    public String getImgUrl() {

        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {

        this.imgUrl = imgUrl;
    }

    public boolean isExplicit() {

        return isExplicit;
    }

    public void setExplicit(boolean explicit) {

        isExplicit = explicit;
    }

    public Contributor getAddedBy() {

        return addedBy;
    }

    public void setAddedBy(Contributor addedBy) {

        this.addedBy = addedBy;
    }

    public LocalDateTime getAddedDate() {

        return addedDate;
    }

    public LocalDateTime getLastModifiedDate() {

        return lastModifiedDate;
    }

    public Contributor getLastModifiedBy() {

        return lastModifiedBy;
    }

    public void setLastModifiedBy(Contributor lastModifiedBy) {

        this.lastModifiedBy = lastModifiedBy;
    }

    public Contributor getPublishedBy() {

        return publishedBy;
    }

    public void setPublishedBy(Contributor publishedBy) {

        this.publishedBy = publishedBy;
    }

    public LocalDateTime getPublishedDate() {

        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {

        this.publishedDate = publishedDate;
    }

    public boolean isPublishedState() {

        return publishedState;
    }

    public void setPublishedState(boolean publishedState) {

        this.publishedState = publishedState;
    }

    public Set<SongGenre> getSongGenres() {

        return songGenres;
    }

    public void setSongGenres(Set<SongGenre> songGenres) {

        this.songGenres = songGenres;
    }

    public Set<ArtistSong> getArtistSongs() {

        return artistSongs;
    }

    public void setArtistSongs(Set<ArtistSong> artistSongs) {

        this.artistSongs = artistSongs;
    }

    public Set<SongModify> getSongModifies() {

        return songModifies;
    }

    public void setSongModifies(Set<SongModify> songModifies) {

        this.songModifies = songModifies;
    }

}
