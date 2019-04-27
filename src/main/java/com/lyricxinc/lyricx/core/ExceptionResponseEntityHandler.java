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

@ControllerAdvice
public class ExceptionResponseEntityHandler {

    private final HttpResponse httpResponse;

    @Autowired
    public ExceptionResponseEntityHandler(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleNotFoundException(WebRequest request) {
        return httpResponse.returnResponse("Requested API Method Doesn't Exists.", request.getDescription(false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundCustomException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleNotFoundCustomException(WebRequest request) {
        return handleNotFoundException(request);
    }

    @ExceptionHandler({MultipartException.class, FileUploadBase.FileSizeLimitExceededException.class})
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleFileUploadErrorException(WebRequest request) {
        return httpResponse.returnResponse("Error while uploading the image. Image should not exceed the size of 3Mb.", request.getDescription(false), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FileUploadErrorCustomException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleFileUploadErrorCustomException(WebRequest request, RuntimeException ex) {
        return httpResponse.returnResponse(ex.getMessage(), request.getDescription(false), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ForbiddenCustomException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleForbiddenCustomException(WebRequest request, RuntimeException ex) {
        return httpResponse.returnResponse(ex.getMessage(), request.getDescription(true), HttpStatus.FORBIDDEN);
    }
}
