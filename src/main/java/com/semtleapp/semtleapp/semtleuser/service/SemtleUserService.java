package com.semtleapp.semtleapp.semtleuser.service;


import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserReq;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserRes;
import com.semtleapp.semtleapp.semtleuser.dto.Token;
import org.springframework.web.multipart.MultipartFile;

public interface SemtleUserService {
    SemtleUserRes.UserDetail signup(SemtleUserReq.SignupDto signupDto, MultipartFile file);

    SemtleUserRes.UserDetail nowUser(String email);

    Token login(SemtleUserReq.LoginDto loginDto, String userAgent);

}
