package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String bandName;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "band", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BandChanter> bandChanters;

    public Band() {
    }

    public Band(@NotBlank String bandName) {
        this.bandName = bandName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Set<BandChanter> getBandChanters() {
        return bandChanters;
    }

    public void setBandChanters(Set<BandChanter> bandChanters) {
        this.bandChanters = bandChanters;
    }
}