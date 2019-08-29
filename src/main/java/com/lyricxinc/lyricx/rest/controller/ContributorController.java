package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.core.request.User;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("contributor")
public interface ContributorController {

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<HttpResponseData> createAccount(String email, char[] password, String firstName, String lastName, String contactLink);

    @PostMapping(value = "getContTest/test")
    ResponseEntity<HttpResponseData> getContTest(HttpServletRequest request, @RequestBody User user);
}
