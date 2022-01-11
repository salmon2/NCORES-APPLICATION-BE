package com.ncores.plaluvs.exception;

import lombok.Getter;

@Getter
public class PlaluvsException extends Exception {
    private final ErrorCode errorCode;

    public PlaluvsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
