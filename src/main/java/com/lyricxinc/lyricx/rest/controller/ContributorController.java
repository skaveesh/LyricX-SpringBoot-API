package com.lyricxinc.lyricx.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("contributor")
public interface ContributorController {
        @PostMapping("authenticate")
    void authContributor(String email);
}
