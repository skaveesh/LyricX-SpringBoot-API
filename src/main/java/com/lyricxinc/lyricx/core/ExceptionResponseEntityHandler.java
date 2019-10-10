package com.lyricxinc.lyricx.core;

import com.lyricxinc.lyricx.core.exception.FileUploadErrorCustomException;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.core.exception.NotFoundCustomException;
import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionResponseEntityHandler {

    private final HttpResponse httpResponse;

    @Autowired
    public ExceptionResponseEntityHandler(HttpResponse httpResponse) {

        this.httpResponse = httpResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleNotFoundException() {

        return httpResponse.returnResponse(HttpStatus.NOT_FOUND, "Requested API Method Doesn't Exists.", null);
    }

    @ExceptionHandler(NotFoundCustomException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleNotFoundCustomException() {

        return handleNotFoundException();
    }

    @ExceptionHandler({MultipartException.class, FileUploadBase.FileSizeLimitExceededException.class})
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleFileUploadErrorException(WebRequest request) {

        return httpResponse.returnResponse(HttpStatus.FORBIDDEN, "Error while uploading the image. Image should not exceed the size of 3Mb.", null);
    }

    @ExceptionHandler(FileUploadErrorCustomException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleFileUploadErrorCustomException(RuntimeException ex) {

        return httpResponse.returnResponse(HttpStatus.FORBIDDEN, ex.getMessage(), null);
    }

    @ExceptionHandler(ForbiddenCustomException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleForbiddenCustomException(RuntimeException ex) {

        return httpResponse.returnResponse(HttpStatus.FORBIDDEN, ex.getMessage(), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleConstraintViolationException(RuntimeException ex) {

        return httpResponse.returnResponse(HttpStatus.FORBIDDEN, ex.getMessage(), null);
    }

}
