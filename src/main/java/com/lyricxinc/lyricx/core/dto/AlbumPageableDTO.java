package com.lyricxinc.lyricx.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AlbumPageableDTO extends PageableDTO {

    private List<AlbumDTO> albumList;

    public AlbumPageableDTO(int totalPages, int currentPage, int pageSize, long totalElements, List<AlbumDTO> albumList) {

        super(totalPages, currentPage, pageSize, totalElements);
        this.albumList = albumList;
    }

}
