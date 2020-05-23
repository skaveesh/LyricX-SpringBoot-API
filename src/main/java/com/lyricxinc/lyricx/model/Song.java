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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * The type Song.
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Song {

    @Id
    @SequenceGenerator(name = "song_id_seq", sequenceName = "song_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "song_id_seq")
    @JsonIgnore
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    @NotNull(groups = OnSongUpdate.class)
    private String surrogateKey;

    @NotBlank(groups = {OnSongCreate.class})
    private String name;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "albumId", nullable = false)
    @JsonBackReference(value = "songReferenceAlbum")
    private Album album;

    @Size(max = 5)
    private String guitarKey;

    @Size(max = 5)
    private String beat;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "languageId", nullable = false)
    @JsonBackReference(value = "referenceLanguage")
    private Language language;

    private String keywords;

    @Lob
    @NotNull(groups = {OnSongCreate.class})
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
    private void onCreate() {

        setSurrogateKey(UUID.randomUUID().toString());
    }

    /**
     * Instantiates a new Song.
     */
    public Song() {

    }

    /**
     * Instantiates a new Song.
     *
     * @param name           the name
     * @param album          the album
     * @param guitarKey      the guitar key
     * @param beat           the beat
     * @param language       the language
     * @param keywords       the keywords
     * @param lyrics         the lyrics
     * @param youTubeLink    the you tube link
     * @param spotifyLink    the spotify link
     * @param deezerLink     the deezer link
     * @param imgUrl         the img url
     * @param isExplicit     the is explicit
     * @param addedBy        the added by
     * @param publishedState the published state
     */
    public Song(@NotBlank String name, Album album, @Size(max = 5) String guitarKey, @Size(max = 5) String beat, Language language, String keywords, @NotBlank String lyrics, String youTubeLink, String spotifyLink, String deezerLink, String imgUrl, boolean isExplicit, Contributor addedBy, boolean publishedState) {

        this.name = name;
        this.album = album;
        this.guitarKey = guitarKey;
        this.beat = beat;
        this.language = language;
        this.keywords = keywords;
        this.lyrics = lyrics.getBytes();
        this.youTubeLink = youTubeLink;
        this.spotifyLink = spotifyLink;
        this.deezerLink = deezerLink;
        this.imgUrl = imgUrl;
        this.isExplicit = isExplicit;
        this.addedBy = addedBy;
        this.publishedState = publishedState;
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
     * Gets album.
     *
     * @return the album
     */
    public Album getAlbum() {

        return album;
    }

    /**
     * Sets album.
     *
     * @param album the album
     */
    public void setAlbum(Album album) {

        this.album = album;
    }

    /**
     * Gets guitar key.
     *
     * @return the guitar key
     */
    public String getGuitarKey() {

        return guitarKey;
    }

    /**
     * Sets guitar key.
     *
     * @param guitarKey the guitar key
     */
    public void setGuitarKey(String guitarKey) {

        this.guitarKey = guitarKey;
    }

    /**
     * Gets beat.
     *
     * @return the beat
     */
    public String getBeat() {

        return beat;
    }

    /**
     * Sets beat.
     *
     * @param beat the beat
     */
    public void setBeat(String beat) {

        this.beat = beat;
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public Language getLanguage() {

        return language;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(Language language) {

        this.language = language;
    }

    /**
     * Gets keywords.
     *
     * @return the keywords
     */
    public String getKeywords() {

        return keywords;
    }

    /**
     * Sets keywords.
     *
     * @param keywords the keywords
     */
    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    /**
     * Gets lyrics.
     *
     * @return the lyrics
     */
    public String getLyrics() {

        return new String(lyrics, StandardCharsets.UTF_8);
    }

    /**
     * Sets lyrics.
     *
     * @param lyrics the lyrics
     */
    public void setLyrics(String lyrics) {

        this.lyrics = lyrics.getBytes();
    }

    /**
     * Gets you tube link.
     *
     * @return the you tube link
     */
    public String getYouTubeLink() {

        return youTubeLink;
    }

    /**
     * Sets you tube link.
     *
     * @param youTubeLink the you tube link
     */
    public void setYouTubeLink(String youTubeLink) {

        this.youTubeLink = youTubeLink;
    }

    /**
     * Gets spotify link.
     *
     * @return the spotify link
     */
    public String getSpotifyLink() {

        return spotifyLink;
    }

    /**
     * Sets spotify link.
     *
     * @param spotifyLink the spotify link
     */
    public void setSpotifyLink(String spotifyLink) {

        this.spotifyLink = spotifyLink;
    }

    /**
     * Gets deezer link.
     *
     * @return the deezer link
     */
    public String getDeezerLink() {

        return deezerLink;
    }

    /**
     * Sets deezer link.
     *
     * @param deezerLink the deezer link
     */
    public void setDeezerLink(String deezerLink) {

        this.deezerLink = deezerLink;
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
     * Is explicit boolean.
     *
     * @return the boolean
     */
    public boolean isExplicit() {

        return isExplicit;
    }

    /**
     * Sets explicit.
     *
     * @param explicit the explicit
     */
    public void setExplicit(boolean explicit) {

        isExplicit = explicit;
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
     * Gets published by.
     *
     * @return the published by
     */
    public Contributor getPublishedBy() {

        return publishedBy;
    }

    /**
     * Sets published by.
     *
     * @param publishedBy the published by
     */
    public void setPublishedBy(Contributor publishedBy) {

        this.publishedBy = publishedBy;
    }

    /**
     * Gets published date.
     *
     * @return the published date
     */
    public LocalDateTime getPublishedDate() {

        return publishedDate;
    }

    /**
     * Sets published date.
     *
     * @param publishedDate the published date
     */
    public void setPublishedDate(LocalDateTime publishedDate) {

        this.publishedDate = publishedDate;
    }

    /**
     * Is published state boolean.
     *
     * @return the boolean
     */
    public boolean isPublishedState() {

        return publishedState;
    }

    /**
     * Sets published state.
     *
     * @param publishedState the published state
     */
    public void setPublishedState(boolean publishedState) {

        this.publishedState = publishedState;
    }

    /**
     * Gets song genres.
     *
     * @return the song genres
     */
    public Set<SongGenre> getSongGenres() {

        return songGenres;
    }

    /**
     * Sets song genres.
     *
     * @param songGenres the song genres
     */
    public void setSongGenres(Set<SongGenre> songGenres) {

        this.songGenres = songGenres;
    }

    /**
     * Gets artist songs.
     *
     * @return the artist songs
     */
    public Set<ArtistSong> getArtistSongs() {

        System.out.println("getter song");
        return artistSongs;
    }

    /**
     * Sets artist songs.
     *
     * @param artistSongs the artist songs
     */
    public void setArtistSongs(Set<ArtistSong> artistSongs) {
        System.out.println("getter song");

        this.artistSongs = artistSongs;
    }

    /**
     * Gets song modifies.
     *
     * @return the song modifies
     */
    public Set<SongModify> getSongModifies() {

        return songModifies;
    }

    /**
     * Sets song modifies.
     *
     * @param songModifies the song modifies
     */
    public void setSongModifies(Set<SongModify> songModifies) {

        this.songModifies = songModifies;
    }

}
