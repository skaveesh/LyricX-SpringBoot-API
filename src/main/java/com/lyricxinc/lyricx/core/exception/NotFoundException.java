package com.lyricxinc.lyricx.core.exception;

import com.lyricxinc.lyricx.core.constant.Constants;

public class NotFoundException extends LyricxBaseException {

    public NotFoundException(Constants.ErrorMessageAndCode err) {

        super(err.getErrorMessage(), err.name());
    }

}
