package com.semtleapp.semtleapp.semtleuser.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.entity.CustomResponse;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.global.jwt.JwtService;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserReq;
import com.semtleapp.semtleapp.semtleuser.dto.Token;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.semtleuser.service.SemtleUserService;
import com.semtleapp.semtleapp.semtleuser.service.SemtleUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.semtleapp.semtleapp.global.exception.ErrorCode.BAD_REQUEST;

@RequiredArgsConstructor
@RestController
@Api(tags={"01.User❤️"})
@RequestMapping("/user")
public class SemtleUserController {
    private final SemtleUserService semtleUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping("/signup")
    public ApiResponse<SemtleUserDto> signup(@RequestBody SemtleUserReq.Login semtleUserDto) {
        //TODO DTO 수정하기
        semtleUserDto.setPassword(passwordEncoder.encode(semtleUserDto.getPassword()));

        return new ApiResponse<>(semtleUserService.create(semtleUserDto));
    }

    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public ApiResponse<Token> login(HttpServletRequest request, @RequestBody SemtleUserDto semtleUserDto,
                                    @RequestHeader(value = "User-Agent", required = false) String userAgent) {
    //TODO DTO 수정하기
        return new ApiResponse<>(jwtService.login(request, semtleUserDto, userAgent));
    }

    @ApiOperation(value = "현재 유저 조회", notes = "현재 유저 조회, 쿼리스트링 입력 필요 없음")
    @GetMapping()
    public ApiResponse<SemtleUserDto> nowUser(Principal principal){
        //principal.getName() 하면 시큐리티에서 들어온 회원의 이메일을 가지고옴.
        //TODO 예외처리 추가 예정

        if(principal.getName() ==  null)
            throw new CustomException(BAD_REQUEST);

        return new ApiResponse<>(semtleUserService.nowUser(principal.getName()));
    }
}
