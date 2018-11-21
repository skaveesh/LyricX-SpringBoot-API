package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.ResponseData;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("contributor")
public interface ContributorController {
    @PostMapping(value = "register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<ResponseData> createAccount(String email, char password, String firstName, String lastName, String contactLink);
}
