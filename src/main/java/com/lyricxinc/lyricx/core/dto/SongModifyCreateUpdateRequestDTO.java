package com.lyricxinc.lyricx.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongModifyCreateUpdateRequestDTO {

    private SongCreateUpdateRequestDTO song;

    private String lyrics;

    private ContributorCreateUpdateRequestDTO contributor;

    private String lastModifiedDate;

    private Boolean hiddenStatus;
}
