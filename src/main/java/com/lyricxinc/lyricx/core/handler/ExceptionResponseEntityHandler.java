package com.lyricxinc.lyricx.core.handler;

import com.lyricxinc.lyricx.core.exception.FileUploadException;
import com.lyricxinc.lyricx.core.exception.ForbiddenException;
import com.lyricxinc.lyricx.core.exception.LyricxBaseException;
import com.lyricxinc.lyricx.core.exception.NotFoundException;
import com.lyricxinc.lyricx.core.response.HttpResponse;
import com.lyricxinc.lyricx.core.response.HttpResponseData;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static com.lyricxinc.lyricx.core.constant.Constants.ErrorCode;
import static com.lyricxinc.lyricx.core.constant.Constants.ErrorMessage;

@ControllerAdvice
public class ExceptionResponseEntityHandler {

    private final HttpResponse httpResponse;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResponseEntityHandler.class);

    @Autowired
    public ExceptionResponseEntityHandler(HttpResponse httpResponse) {

        this.httpResponse = httpResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleNotFoundException() {

        return httpResponse.returnResponse(HttpStatus.NOT_FOUND, ErrorMessage.LYRICX_ERR_01, ErrorCode.LYRICX_ERR_01, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleException() {

        return httpResponse.returnResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.LYRICX_ERR_00, ErrorCode.LYRICX_ERR_00, null);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleNotFoundCustomException() {

        return handleNotFoundException();
    }

    @ExceptionHandler({MultipartException.class, FileUploadBase.FileSizeLimitExceededException.class})
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleFileUploadErrorException(WebRequest request) {

        return httpResponse.returnResponse(HttpStatus.FORBIDDEN, ErrorMessage.LYRICX_ERR_03, ErrorCode.LYRICX_ERR_03, null);
    }

    @ExceptionHandler(FileUploadException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleFileUploadErrorCustomException(LyricxBaseException ex) {

        logError(ex);
        return httpResponse.returnResponse(HttpStatus.FORBIDDEN, ex.getMessage(), ex.getCode(), null);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleForbiddenCustomException(LyricxBaseException ex) {

        logError(ex);
        return httpResponse.returnResponse(HttpStatus.FORBIDDEN, ex.getMessage(), ex.getCode(), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleConstraintViolationException(RuntimeException ex) {

        logError(ex);
        return handleException();
    }

    @ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<HttpResponseData> handleMySQLIntegrityConstraintViolationException(RuntimeException ex) {

        logError(ex);
        return httpResponse.returnResponse(HttpStatus.FORBIDDEN, ErrorMessage.LYRICX_ERR_02, ErrorCode.LYRICX_ERR_02, null);
    }

    private void logError(Exception ex) {

        if (ex instanceof LyricxBaseException && ex.getMessage() != null && ((LyricxBaseException) ex).getCode() != null)
        {
            LOGGER.error("Error occurred. Error Message : {}, Error Code : {}.", ex.getMessage(), ((LyricxBaseException) ex).getCode(), ex);
        }
        else if (ex.getMessage() != null)
        {
            LOGGER.error("Error occurred. Error Message : {}", ex.getMessage(), ex);
        }
        else
        {
            LOGGER.error("Error occurred : ", ex);
        }
    }

}
