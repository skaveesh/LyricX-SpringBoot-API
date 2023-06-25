package com.lyricxinc.lyricx.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SongPageableDTO extends PageableDTO {

    private List<SongDTO> songList;

    public SongPageableDTO(int totalPages, int currentPage, int pageSize, long totalElements, List<SongDTO> songList) {

        super(totalPages, currentPage, pageSize, totalElements);
        this.songList = songList;
    }

}
