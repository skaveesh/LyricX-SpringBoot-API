package com.lyricxinc.lyricx.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ArtistPageableDTO extends PageableDTO {

    private List<ArtistDTO> artistList;

    public ArtistPageableDTO(int totalPages, int currentPage, int pageSize, long totalElements, List<ArtistDTO> artistList) {

        super(totalPages, currentPage, pageSize, totalElements);
        this.artistList = artistList;
    }

}
