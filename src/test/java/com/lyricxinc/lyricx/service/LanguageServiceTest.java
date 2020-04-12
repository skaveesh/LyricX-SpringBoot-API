package com.lyricxinc.lyricx.service;

import com.lyricxinc.lyricx.model.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageServiceTest {

    @Autowired
    LanguageService languageService;

    private Language language = new Language("English");

    @Test
    public void addLanguage() {

        languageService.addLanguage(language);
    }

}
