package com.iut.couchut.banking.web.exception;

/**
 * Created by Valentin on 08/02/2015.
 */
public class DataIntegrityException extends RuntimeException {

    private ErrorCode errorCode;

    public DataIntegrityException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
