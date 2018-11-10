package com.lyricxinc.lyricx.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public interface HomeController {
    @RequestMapping("/")
    String home();
}
