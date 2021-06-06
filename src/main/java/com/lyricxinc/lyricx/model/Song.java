package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lyricxinc.lyricx.model.validator.group.OnSongCreate;
import com.lyricxinc.lyricx.model.validator.group.OnSongUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * The type Song.
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor
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
    @Type(type = "org.hibernate.type.BinaryType")
    @NotNull(groups = {OnSongCreate.class})
    @Column(length = 100000)
    private byte[] lyrics;

    private String youTubeLink;

    private String spotifyLink;

    private String deezerLink;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imgUrl;

    private Boolean isExplicit;

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
    private Boolean publishedState;

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
    public Song(@NotBlank String name, Album album, @Size(max = 5) String guitarKey, @Size(max = 5) String beat, @NotBlank Language language, String keywords, @NotBlank String lyrics, String youTubeLink, String spotifyLink, String deezerLink, String imgUrl, Boolean isExplicit, Contributor addedBy, Boolean publishedState) {

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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(@NotBlank String name) {

        this.name = name;
    }

    /**
     * Sets guitar key.
     *
     * @param guitarKey the guitar key
     */
    public void setGuitarKey(@Size(max = 5) String guitarKey) {

        this.guitarKey = guitarKey;
    }

    /**
     * Sets beat.
     *
     * @param beat the beat
     */
    public void setBeat(@Size(max = 5) String beat) {

        this.beat = beat;
    }

    /**
     * Sets language.
     *
     * @param language the language
     */
    public void setLanguage(@NotBlank Language language) {

        this.language = language;
    }

    /**
     * Sets lyrics.
     *
     * @param lyrics the lyrics
     */
    public void setLyrics(@NotBlank byte[] lyrics) {

        this.lyrics = lyrics;
    }

    /**
     * Sets song genres.
     *
     * @param songGenres the song genres
     */
    public void setSongGenres(Set<SongGenre> songGenres) {

        if (this.songGenres == null) {
            this.songGenres = songGenres;
        } else {
            this.songGenres.clear();
            this.songGenres.addAll(songGenres);
        }
    }

    /**
     * Sets artist songs.
     *
     * @param artistSongs the artist songs
     */
    public void setArtistSongs(Set<ArtistSong> artistSongs) {

        if (this.artistSongs == null) {
            this.artistSongs = artistSongs;
        } else {
            this.artistSongs.clear();
            this.artistSongs.addAll(artistSongs);
        }
    }

    /**
     * Sets song modifies.
     *
     * @param songModifies the song modifies
     */
    public void setSongModifies(Set<SongModify> songModifies) {

        if (this.songModifies == null) {
            this.songModifies = songModifies;
        } else {
            this.songModifies.clear();
            this.songModifies.addAll(songModifies);
        }
    }

}
