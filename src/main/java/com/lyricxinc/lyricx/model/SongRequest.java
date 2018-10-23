package com.lyricxinc.lyricx.model;

import javax.persistence.*;
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
}
