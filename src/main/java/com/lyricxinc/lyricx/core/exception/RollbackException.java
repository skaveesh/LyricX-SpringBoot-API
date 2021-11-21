package com.lyricxinc.lyricx.core.exception;

import com.lyricxinc.lyricx.core.constant.Constants;

public class RollbackException extends LyricxBaseException {

    public RollbackException(Constants.ErrorMessageAndCode err) {

        super(err.getErrorMessage(), err.name());
    }

}
