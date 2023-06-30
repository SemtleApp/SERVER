package com.semtleapp.semtleapp.semtleuser.service;


import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;

public interface SemtleUserService {
    SemtleUserDto create(SemtleUserDto semtleUserDto);

    SemtleUserDto nowUser(String email);

}
