package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.model.Language;
import com.lyricxinc.lyricx.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {

        this.languageRepository = languageRepository;
    }

    public Language getLanguageById(short id) {

        Language language = languageRepository.findById(id).orElse(null);

        if (language == null)
            throw new ForbiddenException(ErrorMessage.LYRICX_ERR_14, ErrorCode.LYRICX_ERR_14);

        return language;
    }

    public Language findLanguageByLanguageCode(String languageCode) {

        return languageRepository.findByLanguageCode(languageCode.toUpperCase());
    }

    public void addLanguage(Language language) {

        languageRepository.save(language);
    }

}
