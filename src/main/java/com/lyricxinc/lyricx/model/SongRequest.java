package com.lyricxinc.lyricx.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class SongRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String songName;

    private String artistName;

    private String albumName;

    @NotBlank
    private String requesterName;

    @NotBlank
    private String requesterEmail;

    public SongRequest() {

    }

    public SongRequest(@NotBlank String songName, String artistName, String albumName, @NotBlank String requesterName, @NotBlank String requesterEmail) {

        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.requesterName = requesterName;
        this.requesterEmail = requesterEmail;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getSongName() {

        return songName;
    }

    public void setSongName(String songName) {

        this.songName = songName;
    }

    public String getArtistName() {

        return artistName;
    }

    public void setArtistName(String artistName) {

        this.artistName = artistName;
    }

    public String getAlbumName() {

        return albumName;
    }

    public void setAlbumName(String albumName) {

        this.albumName = albumName;
    }

    public String getRequesterName() {

        return requesterName;
    }

    public void setRequesterName(String requesterName) {

        this.requesterName = requesterName;
    }

    public String getRequesterEmail() {

        return requesterEmail;
    }

    public void setRequesterEmail(String requesterEmail) {

        this.requesterEmail = requesterEmail;
    }

}
