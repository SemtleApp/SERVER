package com.semtleapp.semtleapp.semtleuser.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.global.jwt.JwtService;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserReq;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserRes;
import com.semtleapp.semtleapp.semtleuser.dto.Token;
import com.semtleapp.semtleapp.semtleuser.service.SemtleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

import static com.semtleapp.semtleapp.global.exception.ErrorCode.BAD_REQUEST;

@RequiredArgsConstructor
@RestController
@Api(tags={"01.User❤️"})
@RequestMapping("/user")
public class SemtleUserController {
    private final SemtleUserService semtleUserService;

    private final JwtService jwtService;


    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping("/signup")
    public ApiResponse<SemtleUserRes.UserDetail> signup(@RequestPart(value = "signupDto") SemtleUserReq.SignupDto signupDto,
                                                        @RequestPart(value = "file", required = false) MultipartFile file
                                                        ) {

        SemtleUserRes.UserDetail result = semtleUserService.signup(signupDto, file);
        return new ApiResponse<>(result);
    }

    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public ApiResponse<Token> login(@RequestBody SemtleUserReq.LoginDto loginDto,
                                    @RequestHeader(value = "User-Agent", required = false) String userAgent) {

        return new ApiResponse<>(semtleUserService.login(loginDto, userAgent));
    }

    @ApiOperation(value = "현재 유저 조회", notes = "현재 유저 조회, 쿼리스트링 입력 필요 없음")
    @GetMapping()
    public ApiResponse<SemtleUserRes.UserDetail> nowUser(Principal principal){
        //principal.getName() 하면 시큐리티에서 들어온 회원의 이메일을 가지고옴.
        //TODO 예외처리 추가 예정

        if(principal.getName().isEmpty())
            throw new CustomException(BAD_REQUEST);

        return new ApiResponse<>(semtleUserService.nowUser(principal.getName()));
    }




}
