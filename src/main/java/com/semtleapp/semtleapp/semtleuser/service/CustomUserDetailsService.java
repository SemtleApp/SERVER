package com.semtleapp.semtleapp.semtleuser.service;

import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.semtleapp.semtleapp.global.exception.ErrorCode.DUPLICATE_EMAIL;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final SemtleUserRepository semtleUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws CustomException {
        return semtleUserRepository.findByEmail(username).orElseThrow(
                () -> new CustomException(DUPLICATE_EMAIL));
    }
}
