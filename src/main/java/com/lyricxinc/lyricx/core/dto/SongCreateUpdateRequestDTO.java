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

    private String imgUrl;

    private Boolean isExplicit;

    private ContributorCreateUpdateRequestDTO addedBy;

    private ContributorCreateUpdateRequestDTO lastModifiedBy;

    private ContributorCreateUpdateRequestDTO publishedBy;

    private String addedDate;

    private String lastModifiedDate;

    private String publishedDate;

    private Boolean publishedState;

    // songModifies excluded due to it's not needed when requesting a song
    // private SongModifyCreateUpdateRequestDTO songModifies;
    private boolean songModifiesRequestsAvailable;

    private List<String> artistSurrogateKeyList;

    private List<Short> genreIdList;
}
