package com.lyricxinc.lyricx.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContributorCreateUpdateRequestDTO {

    private String firstName;

    private String lastName;

    private String description;

    private String imgUrl;

    private String contactLink;

    private Boolean seniorContributor;

    private String addedDate;

    private String lastModifiedDate;
}
