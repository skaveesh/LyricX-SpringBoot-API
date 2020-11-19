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
public class ArtistCreateUpdateRequestDTO {

    private String surrogateKey;

    private String name;

    private Boolean approvedStatus;

    private List<Short> genreIdList;

}
