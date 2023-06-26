package com.semtleapp.semtleapp.global.test;


import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.semtleapp.semtleapp.global.exception.ErrorCode.BAD_REQUEST;
import static com.semtleapp.semtleapp.global.exception.ErrorCode.USERNAME_OR_PASSWORD_NOT_FOUND_EXCEPTION;

@RequiredArgsConstructor
@Api(tags={"00. Controller 예시"})
@RequestMapping("/test")
@RestController
public class testController {

//    @GetMapping("/success")
//    public ApiResponse<String> success(){
//        return new ApiResponse<>("요청 성공 시 반환되는 객체");
//    }
//
//    @GetMapping("/fail")
//    public ApiResponse<String> customExceptionTest(){
//        throw new CustomException(BAD_REQUEST);
//    }

    @ApiOperation(value = "컨트롤러 예시", notes = "null 입력하면 400오류 발생, 입력하지 않으면 200 성공")
    @GetMapping("")
    public ApiResponse<String> test(@RequestParam(required = false) String s){

        if("null".equals(s))
            throw new CustomException(BAD_REQUEST);

        return new ApiResponse<>("test 성공");
    }

}
