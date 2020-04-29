package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.rest.controller.HomeController;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project lyricx
 * Created by skaveesh on 2018-10-18.
 */

@RestController
public class HomeControllerImpl implements HomeController {

    @Override
    public String home() {

        return "LyricX Web Explorer";
    }

}
