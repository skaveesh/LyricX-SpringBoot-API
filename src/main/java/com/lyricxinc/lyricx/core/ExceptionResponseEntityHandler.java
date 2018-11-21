package com.lyricxinc.lyricx.core;

import com.lyricxinc.lyricx.core.exception.FileUploadErrorCustomException;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.core.exception.NotFoundCustomException;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionResponseEntityHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<ResponseData> handleNotFoundException(WebRequest request) {
        ResponseData responseData = new ResponseData(LocalDateTime.now(), "Requested API Method Doesn't Exists.", request.getDescription(false));
        return new ResponseEntity<>(responseData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundCustomException.class)
    @ResponseBody
    public ResponseEntity<ResponseData> handleNotFoundCustomException(WebRequest request) {
        return handleNotFoundException(request);
    }

    @ExceptionHandler({MultipartException.class,FileUploadBase.FileSizeLimitExceededException.class})
    @ResponseBody
    public ResponseEntity<ResponseData> handleFileUploadErrorException(WebRequest request) {
        ResponseData responseData = new ResponseData(LocalDateTime.now(), "Error while uploading the image. Image should not exceed the size of 3Mb.", request.getDescription(false));
        return new ResponseEntity<>(responseData, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FileUploadErrorCustomException.class)
    @ResponseBody
    public ResponseEntity<ResponseData> handleFileUploadErrorCustomException(WebRequest request, RuntimeException ex) {
        ResponseData responseData = new ResponseData(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(responseData, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ForbiddenCustomException.class)
    @ResponseBody
    public ResponseEntity<ResponseData> handleForbiddenCustomException(WebRequest request, RuntimeException ex) {
        ResponseData responseData = new ResponseData(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(responseData, HttpStatus.FORBIDDEN);
    }
}
