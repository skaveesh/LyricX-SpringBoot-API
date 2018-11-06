package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Chanter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true, updatable = false)
    private String googleIdToken;

    private boolean disabledStatus;

    @CreationTimestamp
    private LocalDateTime joinedDate;

    @OneToMany(mappedBy = "chanter", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<BandChanter> bandChanters;

    @OneToMany(mappedBy = "chanter", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Favourite> favourites;

    @OneToMany(mappedBy = "chanter", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Playlist> playlists;

    @OneToMany(mappedBy = "chanter", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ChanterFriend> chanters;

    @OneToMany(mappedBy = "friend", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ChanterFriend> friends;

    public Chanter() {
    }

    public Chanter(@NotBlank String googleIdToken, boolean disabledStatus) {
        this.googleIdToken = googleIdToken;
        this.disabledStatus = disabledStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoogleIdToken() {
        return googleIdToken;
    }

    public void setGoogleIdToken(String googleIdToken) {
        this.googleIdToken = googleIdToken;
    }

    public boolean isDisabledStatus() {
        return disabledStatus;
    }

    public void setDisabledStatus(boolean disabledStatus) {
        this.disabledStatus = disabledStatus;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public Set<BandChanter> getBandChanters() {
        return bandChanters;
    }

    public void setBandChanters(Set<BandChanter> bandChanters) {
        this.bandChanters = bandChanters;
    }

    public Set<Favourite> getFavourites() {
        return favourites;
    }

    public void setFavourites(Set<Favourite> favourites) {
        this.favourites = favourites;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Set<ChanterFriend> getChanters() {
        return chanters;
    }

    public void setChanters(Set<ChanterFriend> chanters) {
        this.chanters = chanters;
    }

    public Set<ChanterFriend> getFriends() {
        return friends;
    }

    public void setFriends(Set<ChanterFriend> friends) {
        this.friends = friends;
    }
}
