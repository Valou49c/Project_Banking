package com.iut.couchut.banking.web.exception;

/**
 * Created by Valentin on 08/02/2015.
 */
public class NotFoundException extends RuntimeException {

    private ErrorCode errorCode;

    public NotFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }


}
