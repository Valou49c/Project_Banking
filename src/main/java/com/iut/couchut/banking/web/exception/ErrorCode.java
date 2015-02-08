package com.iut.couchut.banking.web.exception;

/**
 * Created by Valentin on 08/02/2015.
 */
public enum ErrorCode {

    NO_ENTITY_FOUND(10001, "No entity found"),
    WRONG_ENTITY_INFORMATION(10002, "Could not create/update entity with wrong informations");

    private int code;
    private String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
