package com.lyricxinc.lyricx.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("contributor")
public interface ContributorController {
    @PostMapping("login")
    void loginContributor(String email, String password);

    @PostMapping("authenticate")
    void authContributor(String email);
}
