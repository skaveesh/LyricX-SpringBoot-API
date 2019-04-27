package com.lyricxinc.lyricx.core.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HttpResponse {

    private final HttpResponseData httpResponseData;

    @Autowired
    public HttpResponse(HttpResponseData httpResponseData) {
        this.httpResponseData = httpResponseData;
    }

    public ResponseEntity<HttpResponseData> returnResponse(String message, String details, HttpStatus httpStatus) {

        httpResponseData.setTimestamp(LocalDateTime.now());
        httpResponseData.setMessage(message);
        httpResponseData.setDetails(details);

        return new ResponseEntity<>(httpResponseData, httpStatus);
    }
}
