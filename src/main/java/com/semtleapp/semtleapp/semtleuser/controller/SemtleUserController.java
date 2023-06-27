package com.semtleapp.semtleapp.semtleuser.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.entity.CustomResponse;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.global.jwt.JwtService;
import com.semtleapp.semtleapp.semtleuser.dto.Token;
import com.semtleapp.semtleapp.semtleuser.service.SemtleUserService;
import com.semtleapp.semtleapp.semtleuser.service.SemtleUserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ApiResponse<SemtleUserDto> signup(@RequestBody SemtleUserDto semtleUserDto) {
        semtleUserDto.setPassword(passwordEncoder.encode(semtleUserDto.getPassword()));

        return new ApiResponse<>(semtleUserService.create(semtleUserDto));
    }

    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public ApiResponse<Token> login(HttpServletRequest request, @RequestBody SemtleUserDto semtleUserDto,
                                    @RequestHeader(value = "User-Agent", required = false) String userAgent) {

        return new ApiResponse<>(jwtService.login(request, semtleUserDto, userAgent));
    }
}
