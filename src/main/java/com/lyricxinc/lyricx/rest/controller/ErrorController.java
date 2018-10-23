package com.lyricxinc.lyricx.rest.controller;

import com.lyricxinc.lyricx.rest.Error;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project lyricx
 * Created by skaveesh on 2018-10-18.
 */

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController, Error {

    @Override
    public String handleError(){
        return "An error has occurred";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
