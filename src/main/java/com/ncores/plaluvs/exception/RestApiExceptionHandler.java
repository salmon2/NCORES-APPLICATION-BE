package com.ncores.plaluvs.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestApiExceptionHandler {

    @ExceptionHandler(value = {PlaluvsException.class})
    public ResponseEntity<Object> handleApiRequestException(PlaluvsException ex) {

        ErrorCode errorCode = ex.getErrorCode();
        String errorMessage = ex.getMessage();

        log.info("errorCode : {}", errorCode);
        log.info("errorMessage : {}", errorMessage);

        return new ResponseEntity<>(errorMessage ,errorCode.getHttpStatus());

    }



}