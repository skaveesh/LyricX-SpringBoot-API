package com.lyricxinc.lyricx.core.response;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
Do not remove the getters nor change their access modifiers from public to any other type
 */
@Component
public class HttpResponseData {

    private LocalDateTime timestamp;
    private String message;
    private Object data;

    public HttpResponseData() {

    }

    void setTimestamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;
    }

    void setMessage(String message) {

        this.message = message;
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

    public Object getData() {

        return data;
    }

}
