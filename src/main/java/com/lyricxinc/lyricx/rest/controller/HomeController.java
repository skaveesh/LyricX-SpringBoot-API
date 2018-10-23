package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.rest.Home;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project lyricx
 * Created by skaveesh on 2018-10-18.
 */

@RestController
public class HomeController implements Home {
    @Override
    public String home(){
        return "LyricX Web Explorer";
    }
}
