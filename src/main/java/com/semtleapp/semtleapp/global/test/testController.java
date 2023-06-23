package com.semtleapp.semtleapp.global.test;


import com.semtleapp.semtleapp.global.exception.ApiRes;
import com.semtleapp.semtleapp.global.exception.BadRequestException;
import com.semtleapp.semtleapp.global.exception.ServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
public class testController {

    @GetMapping("/success")
    public ApiRes<String> success(){
        return new ApiRes<>("요청 성공 시 반환되는 객체");
    }

    @GetMapping("/fail")
    public ApiRes<String> fail(){
        throw new BadRequestException("어떤 오류인지 적는 곳");
    }

}
