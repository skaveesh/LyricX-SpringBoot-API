package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "chanterId", nullable = false)
    @JsonBackReference(value = "favouritesReferenceChanter")
    private Chanter chanter;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "songId", nullable = false)
    @JsonBackReference(value = "ReferenceSong")
    private Song song;

    public Favourite() {

    }

    public Favourite(Chanter chanter, Song song) {

        this.chanter = chanter;
        this.song = song;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public LocalDateTime getLastModifiedDate() {

        return lastModifiedDate;
    }

    public Chanter getChanter() {

        return chanter;
    }

    public void setChanter(Chanter chanter) {

        this.chanter = chanter;
    }

    public Song getSong() {

        return song;
    }

    public void setSong(Song song) {

        this.song = song;
    }

}
