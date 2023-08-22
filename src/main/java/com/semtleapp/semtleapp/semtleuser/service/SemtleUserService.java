package com.semtleapp.semtleapp.semtleuser.service;


import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserReq;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserRes;
import com.semtleapp.semtleapp.semtleuser.dto.Token;

public interface SemtleUserService {
    SemtleUserRes.UserDetail signup(SemtleUserReq.SignupDto signupDto);

    SemtleUserRes.UserDetail nowUser(String email);

    Token login(SemtleUserReq.LoginDto loginDto, String userAgent);

    Long changeUserStatus(Long userId);

}
