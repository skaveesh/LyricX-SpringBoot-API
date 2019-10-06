package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class SongModify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "songId", nullable = false)
    @JsonBackReference
    private Song song;

    @NotBlank
    private String lyrics;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "contributorId", nullable = false)
    @JsonBackReference
    private Contributor contributor;

    @UpdateTimestamp
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

    public long getId() {

        return id;
    }

    public void setId(long id) {

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
