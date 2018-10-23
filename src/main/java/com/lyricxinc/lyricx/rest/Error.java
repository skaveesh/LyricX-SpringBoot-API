package com.lyricxinc.lyricx.rest;

import org.springframework.web.bind.annotation.RequestMapping;

public interface Error {
    @RequestMapping("/error")
    String handleError();
}
