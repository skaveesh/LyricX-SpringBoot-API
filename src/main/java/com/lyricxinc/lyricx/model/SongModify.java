package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class SongModify {

    @Id
    @SequenceGenerator(name = "song_modify_id_seq", sequenceName = "song_modify_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "song_modify_id_seq")
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "songId", nullable = false)
    @JsonBackReference(value = "songModifiesReferenceSong")
    private Song song;

    @NotBlank
    private String lyrics;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "contributorId", nullable = false)
    @JsonBackReference(value = "referenceContributor")
    private Contributor contributor;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModifiedDate;

    private boolean hiddenStatus;

    public SongModify() {

    }

    public SongModify(Song song, @NotBlank String lyrics, Contributor contributor, boolean hiddenStatus) {

        this.song = song;
        this.lyrics = lyrics;
        this.contributor = contributor;
        this.hiddenStatus = hiddenStatus;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Song getSong() {

        return song;
    }

    public void setSong(Song song) {

        this.song = song;
    }

    public String getLyrics() {

        return lyrics;
    }

    public void setLyrics(String lyrics) {

        this.lyrics = lyrics;
    }

    public Contributor getContributor() {

        return contributor;
    }

    public void setContributor(Contributor contributor) {

        this.contributor = contributor;
    }

    public LocalDateTime getLastModifiedDate() {

        return lastModifiedDate;
    }

    public boolean isHiddenStatus() {

        return hiddenStatus;
    }

    public void setHiddenStatus(boolean hiddenStatus) {

        this.hiddenStatus = hiddenStatus;
    }

}
