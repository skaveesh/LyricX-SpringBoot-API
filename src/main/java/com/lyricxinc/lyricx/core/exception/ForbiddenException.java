package com.lyricxinc.lyricx.core.exception;

import com.lyricxinc.lyricx.core.constant.Constants;

public class ForbiddenException extends LyricxBaseException {

    public ForbiddenException(Constants.ErrorMessageAndCode err) {

        super(err.getErrorMessage(), err.name());
    }

}
