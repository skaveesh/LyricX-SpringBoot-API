package com.lyricxinc.lyricx.core.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArtistDTO {

    private String surrogateKey;

    private String name;

    private String imgUrl;

    private String addedById;

    private String lastModifiedById;

    private Boolean approvedStatus;

    private String addedDate;

    private String lastModifiedDate;

    private List<Short> genreIdList;

    private List<String> albumsSurrogateKeyList;

    private List<String> albumsNameList;

    private List<String> artistSongsSurrogateKeyList;

    private List<String> artistSongsNameList;

}
