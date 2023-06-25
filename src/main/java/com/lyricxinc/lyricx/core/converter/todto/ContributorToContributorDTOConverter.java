package com.lyricxinc.lyricx.core.converter.todto;

import com.lyricxinc.lyricx.core.dto.ContributorDTO;
import com.lyricxinc.lyricx.model.Contributor;
import org.springframework.core.convert.converter.Converter;

public class ContributorToContributorDTOConverter implements Converter<Contributor, ContributorDTO> {

    @Override
    public ContributorDTO convert(Contributor source) {

        ContributorDTO contributorDTO = new ContributorDTO();
        contributorDTO.setFirstName(source.getFirstName());
        contributorDTO.setLastName(source.getLastName());
        contributorDTO.setDescription(source.getDescription());
        contributorDTO.setImgUrl(source.getImgUrl());
        contributorDTO.setContactLink(source.getContactLink());
        contributorDTO.setSeniorContributor(source.isSeniorContributor());
        contributorDTO.setAddedDate(source.getAddedDate().toString());
        contributorDTO.setLastModifiedDate(source.getLastModifiedDate().toString());

        return contributorDTO;
    }
}
