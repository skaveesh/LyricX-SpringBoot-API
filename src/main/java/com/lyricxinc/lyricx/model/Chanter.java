package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Chanter {

    @Id
    @JsonIgnore
    private String id;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    @OneToMany(mappedBy = "chanter", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "bandChantersReferenceChanter")
    private Set<BandChanter> bandChanters;

    @OneToMany(mappedBy = "chanter", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "favouritesReferenceChanter")
    private Set<Favourite> favourites;

    @OneToMany(mappedBy = "chanter", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "playlistsReferenceChanter")
    private Set<Playlist> playlists;

    @OneToMany(mappedBy = "chanter", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "chantersReferenceChanter")
    private Set<ChanterFriend> chanters;

    @OneToMany(mappedBy = "friend", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "friendsReferenceFriend")
    private Set<ChanterFriend> friends;

    public Chanter() {

    }

    public Chanter(@NotBlank String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public LocalDateTime getAddedDate() {

        return addedDate;
    }

    public Set<BandChanter> getBandChanters() {

        return bandChanters;
    }

    public void setBandChanters(Set<BandChanter> bandChanters) {

        if (this.bandChanters == null) {
            this.bandChanters = bandChanters;
        } else {
            this.bandChanters.clear();
            this.bandChanters.addAll(bandChanters);
        }
    }

    public Set<Favourite> getFavourites() {

        return favourites;
    }

    public void setFavourites(Set<Favourite> favourites) {

        if (this.favourites == null) {
            this.favourites = favourites;
        } else {
            this.favourites.clear();
            this.favourites.addAll(favourites);
        }
    }

    public Set<Playlist> getPlaylists() {

        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {

        if (this.playlists == null) {
            this.playlists = playlists;
        } else {
            this.playlists.clear();
            this.playlists.addAll(playlists);
        }
    }

    public Set<ChanterFriend> getChanters() {

        return chanters;
    }

    public void setChanters(Set<ChanterFriend> chanters) {

        if (this.chanters == null) {
            this.chanters = chanters;
        } else {
            this.chanters.clear();
            this.chanters.addAll(chanters);
        }
    }

    public Set<ChanterFriend> getFriends() {

        return friends;
    }

    public void setFriends(Set<ChanterFriend> friends) {

        if (this.friends == null) {
            this.friends = friends;
        } else {
            this.friends.clear();
            this.friends.addAll(friends);
        }
    }

}
