package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.response.HttpResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("language")
public interface LanguageController {

    @GetMapping("all")
    ResponseEntity<HttpResponseData> getAllLanguages(HttpServletRequest request);
}
