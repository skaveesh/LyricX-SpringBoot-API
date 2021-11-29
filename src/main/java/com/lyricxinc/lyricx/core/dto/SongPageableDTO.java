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
public class SongPageableDTO {

    private int totalPages;

    private int currentPage;

    private int pageSize;

    private long totalElements;

    private List<SongDTO> songList;

}
