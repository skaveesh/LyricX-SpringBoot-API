package com.lyricxinc.lyricx.model;

import javax.persistence.*;

@Entity
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id")
    private Song song;

    public Favourite() {
    }

    public Favourite(AppUser appUser, Song song) {
        this.appUser = appUser;
        this.song = song;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
