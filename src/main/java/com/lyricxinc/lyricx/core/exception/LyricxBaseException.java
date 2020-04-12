package com.lyricxinc.lyricx.core.exception;

/**
 * The type Lyricx base exception.
 */
public class LyricxBaseException extends RuntimeException {

    private final String message;
    private final String code;

    /**
     * Instantiates a new Lyricx base exception.
     *
     * @param message the message
     * @param code    the code
     */
    public LyricxBaseException(String message, String code) {

        super(message);
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {

        return message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {

        return code;
    }

}
