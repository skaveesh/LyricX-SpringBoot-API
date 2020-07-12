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

    //    public Resource<ResponseEntity<HttpResponseData>> returnResponse(HttpStatus httpStatus, String message, Object data, Link link) {
    public ResponseEntity<HttpResponseData> returnResponse(HttpStatus httpStatus, String message, String errorCode, Object data) {

        httpResponseData.setTimestamp(LocalDateTime.now());
        httpResponseData.setMessage(message);
        httpResponseData.setErrorCode(errorCode);
        httpResponseData.setData(data);
        return new ResponseEntity<>(httpResponseData, httpStatus);
        //        return new Resource<>(new ResponseEntity<>(httpResponseData, httpStatus), link);
    }

}
