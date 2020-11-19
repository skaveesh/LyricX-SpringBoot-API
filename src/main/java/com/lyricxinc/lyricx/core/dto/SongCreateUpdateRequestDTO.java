package com.lyricxinc.lyricx.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongCreateUpdateRequestDTO {

    private String surrogateKey;

    private String name;

    private String albumSurrogateKey;

    private String guitarKey;

    private String beat;

    private String languageCode;

    private String keywords;

    private byte[] lyrics;

    private String youTubeLink;

    private String spotifyLink;

    private String deezerLink;

    private Boolean isExplicit;

    private List<String> artistSurrogateKeyList;

    private List<Short> genreIdList;
}
