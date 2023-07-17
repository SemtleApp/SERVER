package com.semtleapp.semtleapp.semtleuser.service;


import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserReq;

public interface SemtleUserService {
    SemtleUserDto create(SemtleUserReq.Login loginReq);

    SemtleUserDto nowUser(String email);

}
