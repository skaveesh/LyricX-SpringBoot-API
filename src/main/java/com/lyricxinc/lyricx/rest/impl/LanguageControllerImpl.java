package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.lyricxinc.lyricx.rest.controller.LanguageController;
import com.lyricxinc.lyricx.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LanguageControllerImpl implements LanguageController {

    private final LanguageService languageService;
    private final HttpResponse httpResponse;

    @Autowired
    public LanguageControllerImpl(LanguageService languageService, HttpResponse httpResponse) {
        this.languageService = languageService;
        this.httpResponse = httpResponse;
    }

    @Override
    public ResponseEntity<HttpResponseData> getAllGenres(HttpServletRequest request) {

        return httpResponse.returnResponse(HttpStatus.OK, null, null, languageService.getAllLanguages());
    }

}
