package com.semtleapp.semtleapp.semtleuser.controller;

import com.semtleapp.semtleapp.global.entity.CustomResponse;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.global.jwt.JwtService;
import com.semtleapp.semtleapp.semtleuser.service.SemtleUserService;
import com.semtleapp.semtleapp.semtleuser.service.SemtleUserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class SemtleUserController {
    private final SemtleUserService semtleUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public CustomResponse signup(@RequestBody SemtleUserDto semtleUserDto) {
        semtleUserDto.setPassword(passwordEncoder.encode(semtleUserDto.getPassword()));
        return new CustomResponse.ResponseMap(200, "data", semtleUserService.create(semtleUserDto));
    }

    @PostMapping("/login")
    public CustomResponse login(HttpServletRequest request, @RequestBody SemtleUserDto semtleUserDto, @RequestHeader("User-Agent") String userAgent) {
        return new CustomResponse.ResponseMap(200, "data", jwtService.login(request, semtleUserDto, userAgent));
    }
}
