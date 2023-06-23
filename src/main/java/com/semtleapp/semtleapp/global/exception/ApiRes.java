package com.semtleapp.semtleapp.global.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRes<T> {

    private int code;
    private String message;
    private T data;


    //오류일 경우
    public ApiRes(int code, String message) {
        this.code = code;
        this.message = message;
    }


    //성공일 경우
    public ApiRes(T data) {
        this.code = 200;
        this.message = "요청에 성공하였습니다";
        this.data = data;
    }
}