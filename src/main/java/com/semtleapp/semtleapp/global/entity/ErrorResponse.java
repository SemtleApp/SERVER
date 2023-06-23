package com.semtleapp.semtleapp.global.entity;

import com.semtleapp.semtleapp.global.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {
    private int code;
    private String message;
    private HttpStatus status;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ErrorResponse.builder()
                        .status(e.getStatus())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }
}