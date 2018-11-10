package com.lyricxinc.lyricx.core;

import com.lyricxinc.lyricx.core.exception.ContributorSuspendedCustomException;
import com.lyricxinc.lyricx.core.exception.FileUploadErrorCustomException;
import com.lyricxinc.lyricx.core.exception.ForbiddenCustomException;
import com.lyricxinc.lyricx.core.exception.NotFoundCustomException;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionResponseEntityHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleNotFoundException(WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Requested API Method Doesn't Exists.", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundCustomException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleNotFoundCustomException(WebRequest request) {
        return handleNotFoundException(request);
    }

    @ExceptionHandler({MultipartException.class,FileUploadBase.FileSizeLimitExceededException.class,java.lang.IllegalStateException.class})
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleFileUploadErrorException(WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Error while uploading the image. Image should not exceed the size of 3Mb.", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FileUploadErrorCustomException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleFileUploadErrorCustomException(WebRequest request, RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ContributorSuspendedCustomException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleContributorSuspendedCustomException(WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Your account has been suspended. Please contact an admin.", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ForbiddenCustomException.class)
    @ResponseBody
    public ResponseEntity<ErrorDetails> handleForbiddenCustomException(WebRequest request, RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }
}
