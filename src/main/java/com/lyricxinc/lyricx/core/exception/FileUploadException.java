package com.lyricxinc.lyricx.core.exception;

import com.lyricxinc.lyricx.core.constant.Constants;

public class FileUploadException extends LyricxBaseException {

    public FileUploadException(Constants.ErrorMessageAndCode err) {

        super(err.getErrorMessage(), err.name());
    }

}
