package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.core.dto.LanguageDTO;
import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.model.Language;
import com.lyricxinc.lyricx.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessageAndCode.LYRICX_ERR_14;

/**
 * The type Language service.
 */
@Service
@Transactional
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final ConversionService conversionService;

    /**
     * Instantiates a new Language service.
     *
     * @param languageRepository the language repository
     * @param conversionService  the conversion service
     */
    @Autowired
    public LanguageService(LanguageRepository languageRepository, ConversionService conversionService) {

        this.languageRepository = languageRepository;
        this.conversionService = conversionService;
    }

    /**
     * Gets language by id.
     *
     * @param id the id
     * @return the language by id
     */
    public Language getLanguageById(final Short id) {

        return languageRepository.findById(id).orElseThrow(() -> new NotFoundException(LYRICX_ERR_14));
    }

    /**
     * Gets language by language code.
     *
     * @param languageCode the language code
     * @return the language by language code
     */
    public Language getLanguageByLanguageCode(String languageCode) {

        return languageRepository.findByLanguageCode(languageCode.toLowerCase()).orElseThrow(() -> new NotFoundException(LYRICX_ERR_14));
    }

    /**
     * Gets all genres.
     *
     * @return the all genres
     */
    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll().stream().map(language -> conversionService.convert(language, LanguageDTO.class))
                .collect(Collectors.toList());
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
