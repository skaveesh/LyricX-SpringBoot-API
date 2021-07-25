package com.lyricxinc.lyricx.core.response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
Do not remove the getters nor change their access modifiers from public to any other type
 */
@Component
@Scope("prototype")
public class HttpResponseData {

    private LocalDateTime timestamp;
    private String message;
    private String errorCode;
    private Object data;


    public HttpResponseData() {
        // Empty constructor for Spring initialization
    }

    void setTimestamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;
    }

    void setMessage(String message) {

        this.message = message;
    }

    public void setErrorCode(String errorCode) {

        this.errorCode = errorCode;
    }

    public void setData(Object data) {

        this.data = data;
    }

    public LocalDateTime getTimestamp() {

        return timestamp;
    }

    public String getMessage() {

        return message;
    }

    public String getErrorCode() {

        return errorCode;
    }

    public Object getData() {

        return data;
    }

}
