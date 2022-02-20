package com.lyricxinc.lyricx.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
abstract class PageableDTO {
    private int totalPages;

    private int currentPage;

    private int pageSize;

    private long totalElements;
}
