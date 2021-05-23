package com.lyricxinc.lyricx.core.converter;

import com.lyricxinc.lyricx.core.dto.LanguageResponseDTO;
import com.lyricxinc.lyricx.model.Language;
import org.springframework.core.convert.converter.Converter;

public class LanguageToLanguageResponseDTOConverter implements Converter<Language, LanguageResponseDTO> {

    @Override
    public LanguageResponseDTO convert(Language source) {

        LanguageResponseDTO languageResponseDTO = new LanguageResponseDTO();
        languageResponseDTO.setId(source.getId());
        languageResponseDTO.setLanguageName(source.getLanguageName());
        languageResponseDTO.setLanguageCode(source.getLanguageCode());

        return languageResponseDTO;
    }

}
