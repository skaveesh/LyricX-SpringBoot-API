package com.lyricxinc.lyricx.core.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AlbumDTO {

    private String surrogateKey;

    private String artistSurrogateKey;

    private String artistName;

    private String year;

    private String name;

    private String imgUrl;

    private String addedDate;

    private String lastModifiedDate;

    private String addedById;

    private String lastModifiedById;

    private Boolean approvedStatus;

    private Set<String> songsSurrogateKeys;

}
