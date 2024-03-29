package com.lyricxinc.lyricx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class SongRequest {

    @Id
    @SequenceGenerator(name = "song_request_id_seq", sequenceName = "song_request_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "song_request_id_seq")
    @JsonIgnore
    private Long id;

    @NotBlank
    private String songName;

    private String artistName;

    private String albumName;

    @NotBlank
    private String requesterName;

    @NotBlank
    private String requesterEmail;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime addedDate;

    public SongRequest() {

    }

    public SongRequest(@NotBlank String songName, String artistName, String albumName, @NotBlank String requesterName, @NotBlank String requesterEmail) {

        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.requesterName = requesterName;
        this.requesterEmail = requesterEmail;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

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

    public LocalDateTime getAddedDate() {

        return addedDate;
    }

}
