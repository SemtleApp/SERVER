package com.semtleapp.semtleapp.semtleuser.service;

import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.global.handler.CustomException;
import com.semtleapp.semtleapp.global.handler.ErrorCode;
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
    public SemtleUserDto create(SemtleUserDto semtleUserDto) {
        semtleUserRepository.findByEmail(semtleUserDto.getEmail()).ifPresent(e -> {
            throw new CustomException(ErrorCode.REFISTEREDEMAIL);
        });
        SemtleUser user = semtleUserRepository.save(semtleUserDto.toEntity());

        return user.toDto();
    }



}
