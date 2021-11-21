package com.lyricxinc.lyricx.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContributorDTO {

    private String firstName;

    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    private String imgUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contactLink;

    private Boolean seniorContributor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String addedDate;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastModifiedDate;
}
