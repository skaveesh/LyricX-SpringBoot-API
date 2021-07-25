package com.lyricxinc.lyricx.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongModifyDTO {

    private SongDTO song;

    private String lyrics;

    private ContributorDTO contributor;

    private String lastModifiedDate;

    private Boolean hiddenStatus;
}
