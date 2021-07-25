package com.lyricxinc.lyricx.core.converter.tomodel;

import com.lyricxinc.lyricx.core.dto.LanguageDTO;
import com.lyricxinc.lyricx.model.Language;
import org.springframework.core.convert.converter.Converter;

public class LanguageDTOToLanguageConverter implements Converter<Language, LanguageDTO> {

    @Override
    public LanguageDTO convert(Language source) {

        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(source.getId());
        languageDTO.setLanguageName(source.getLanguageName());
        languageDTO.setLanguageCode(source.getLanguageCode());

        return languageDTO;
    }

}
