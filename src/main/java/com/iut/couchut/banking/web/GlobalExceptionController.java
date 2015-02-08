package com.iut.couchut.banking.web;

import com.iut.couchut.banking.web.exception.DataIntegrityException;
import com.iut.couchut.banking.web.exception.ErrorInfo;
import com.iut.couchut.banking.web.exception.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import static com.iut.couchut.banking.web.helper.HttpHelper.getUri;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by Valentin on 08/02/2015.
 */
@ControllerAdvice
public class GlobalExceptionController {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorInfo handleNotFound(WebRequest request, NotFoundException exception) {
        return new ErrorInfo.Builder(getUri(request), exception.getErrorCode()).build();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(DataIntegrityException.class)
    @ResponseBody
    public ErrorInfo handleDateIntegrityException(WebRequest request, DataIntegrityException exception) {
        return new ErrorInfo.Builder(getUri(request), exception.getErrorCode()).build();
    }



}
