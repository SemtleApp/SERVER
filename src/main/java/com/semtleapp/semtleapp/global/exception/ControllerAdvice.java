package com.semtleapp.semtleapp.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerAdvice {

    //400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ApiRes<String>> handleBadRequestException(BadRequestException e) {
        ApiRes<String> apiRes = new ApiRes<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(apiRes, HttpStatus.BAD_REQUEST);
    }


    //500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ServerErrorException.class})
    public ResponseEntity<ApiRes<String>> handleServerErrorException(ServerErrorException e) {
        ApiRes<String> apiRes = new ApiRes<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(apiRes, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
