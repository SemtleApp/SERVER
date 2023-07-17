package com.semtleapp.semtleapp.semtleuser.service;

import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserReq;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.global.exception.ErrorCode;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SemtleUserServiceImpl implements SemtleUserService, UserDetailsService {
    private final SemtleUserRepository semtleUserRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return semtleUserRepository.findByEmail(username).orElseThrow(
                () -> new RuntimeException());
    }

    @Override
    public SemtleUserDto create(SemtleUserReq.Login loginReq) {
        semtleUserRepository.findByEmail(loginReq.getEmail()).ifPresent(e -> {
            throw new CustomException(ErrorCode.REGISTERED_EMAIL);
        });


        SemtleUser user = semtleUserRepository.save(loginReq.toEntity());

        return user.toDto();
    }

    @Override
    public SemtleUserDto nowUser(String email) {
        SemtleUser semtleUser = semtleUserRepository.findByEmail(email).get();
        return new SemtleUserDto(semtleUser.getEmail(), semtleUser.getPassword());
    }


}
