package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.rest.controller.ContributorController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContributorControllerImpl implements ContributorController {

    @Override
    public void authContributor(String email) {
        System.out.println("auth cont");
    }
}
