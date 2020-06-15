package com.lyricxinc.lyricx.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumCreateUpdateRequest {

    private String surrogateKey;

    private String name;

    private String artistSurrogateKey;

    private Year year;

    private Boolean approvedStatus;
}
