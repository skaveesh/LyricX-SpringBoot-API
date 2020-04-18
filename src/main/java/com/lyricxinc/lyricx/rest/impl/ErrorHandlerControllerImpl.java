package com.lyricxinc.lyricx.rest.impl;

import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.rest.controller.ErrorHandlerController;
import org.springframework.web.bind.annotation.RestController;

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

/**
 * Project lyricx
 * Created by skaveesh on 2018-10-18.
 */

@RestController
public class ErrorHandlerControllerImpl implements org.springframework.boot.web.servlet.error.ErrorController, ErrorHandlerController {

    @Override
    public String handleError() {

        throw new NotFoundException(ErrorMessage.LYRICX_ERR_15, ErrorCode.LYRICX_ERR_15);
    }

    @Override
    public String getErrorPath() {

        return null;
    }

}
