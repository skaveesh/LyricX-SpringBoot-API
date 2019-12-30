package com.lyricxinc.lyricx.core.exception;

public class LyricxBaseException extends RuntimeException {

    private final String message;
    private final String code;

    public LyricxBaseException(String message, String code){
        super(message);
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {

        return message;
    }

    public String getCode() {

        return code;
    }

}
