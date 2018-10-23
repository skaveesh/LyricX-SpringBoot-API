package com.lyricxinc.lyricx.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(unique = true, updatable = false)
    private String googleIdToken;

    private boolean disabledStatus;

    @CreationTimestamp
    private LocalDateTime joinedDate;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupUser> usersInGroup;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Favourite> favourites;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Playlist> playlists;

    public AppUser() {
    }

    public AppUser(@NotBlank String googleIdToken, boolean disabledStatus, LocalDateTime joinedDate) {
        this.googleIdToken = googleIdToken;
        this.disabledStatus = disabledStatus;
        this.joinedDate = joinedDate;
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

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Set<GroupUser> getUsersInGroup() {
        return usersInGroup;
    }

    public void setUsersInGroup(Set<GroupUser> usersInGroup) {
        this.usersInGroup = usersInGroup;
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
}
