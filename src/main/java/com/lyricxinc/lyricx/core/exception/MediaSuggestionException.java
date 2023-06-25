package com.lyricxinc.lyricx.core.exception;

import com.lyricxinc.lyricx.core.constant.Constants;

public class MediaSuggestionException extends LyricxBaseException {

    public MediaSuggestionException(Constants.ErrorMessageAndCode err) {

        super(err.getErrorMessage(), err.name());
    }

}
