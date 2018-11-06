package com.lyricxinc.lyricx.core;

import com.lyricxinc.lyricx.core.exception.NotFoundCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
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
}
