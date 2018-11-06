package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Language;
import com.lyricxinc.lyricx.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language findLanguageByName(String name){ return languageRepository.findByLanguage(name); }

    public void addLanguage(Language language){
        languageRepository.save(language);
    }

}
