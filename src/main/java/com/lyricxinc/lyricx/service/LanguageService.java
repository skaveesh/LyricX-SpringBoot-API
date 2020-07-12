package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.model.Language;
import com.lyricxinc.lyricx.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.*;

/**
 * The type Language service.
 */
@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    /**
     * Instantiates a new Language service.
     *
     * @param languageRepository the language repository
     */
    @Autowired
    public LanguageService(LanguageRepository languageRepository) {

        this.languageRepository = languageRepository;
    }

    /**
     * Gets language by id.
     *
     * @param id the id
     * @return the language by id
     */
    public Language getLanguageById(final Short id) {

        return languageRepository.findById(id).orElseThrow(() -> new ForbiddenException(LYRICX_ERR_14.getErrorMessage(), LYRICX_ERR_14.name()));
    }

    /**
     * Gets language by language code.
     *
     * @param languageCode the language code
     * @return the language by language code
     */
    public Language getLanguageByLanguageCode(String languageCode) {

        return languageRepository.findByLanguageCode(languageCode.toLowerCase()).orElseThrow(() -> new ForbiddenException(LYRICX_ERR_14.getErrorMessage(), LYRICX_ERR_14.name()));
    }

    /**
     * Add language.
     *
     * @param language the language
     */
    public void addLanguage(Language language) {

        languageRepository.save(language);
    }

}
