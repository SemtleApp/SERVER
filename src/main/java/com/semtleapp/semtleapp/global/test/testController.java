package com.semtleapp.semtleapp.global.test;


import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.semtleapp.semtleapp.global.exception.ErrorCode.BAD_REQUEST;
import static com.semtleapp.semtleapp.global.exception.ErrorCode.USERNAME_OR_PASSWORD_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class testController {

    @GetMapping("/success")
    public ApiResponse<String> success(){
        return new ApiResponse<>("요청 성공 시 반환되는 객체");
    }

    @GetMapping("/fail")
    public ApiResponse<String> customExceptionTest(){
        throw new CustomException(BAD_REQUEST);
    }

    @GetMapping("")
    public ApiResponse<String> test(@RequestParam(required = false) String s){

        if("null".equals(s))
            throw new CustomException(BAD_REQUEST);


        return new ApiResponse<>("test 성공");
    }

}
