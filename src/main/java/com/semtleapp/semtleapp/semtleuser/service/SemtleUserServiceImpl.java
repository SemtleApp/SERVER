package com.semtleapp.semtleapp.semtleuser.service;

import com.semtleapp.semtleapp.global.jwt.JwtProvider;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserReq;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserRes;
import com.semtleapp.semtleapp.semtleuser.dto.Token;
import com.semtleapp.semtleapp.semtleuser.entity.RefreshToken;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.global.exception.ErrorCode;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import com.semtleapp.semtleapp.semtleuser.repository.RefreshTokenRepository;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserInfoRepository;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SemtleUserServiceImpl implements SemtleUserService, UserDetailsService {
    private final SemtleUserRepository semtleUserRepository;
    private final SemtleUserInfoRepository  semtleUserInfoRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return semtleUserRepository.findByEmail(username).orElseThrow(
                () -> new RuntimeException());
    }

    @Transactional
    @Override
    public SemtleUserRes.UserDetail signup(SemtleUserReq.SignupDto signupDto) {
        semtleUserRepository.findByEmail(signupDto.getEmail()).ifPresent(e -> {
            throw new CustomException(ErrorCode.REGISTERED_EMAIL);
        });

        SemtleUser user = SemtleUser.builder()
                .email(signupDto.getEmail())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .build();
        semtleUserRepository.save(user);

        SemtleUserInfo userInfo = SemtleUserInfo.builder()
                .name(signupDto.getName())
                .nickname(signupDto.getNickname())
                .grade(signupDto.getGrade())
                .studentId(signupDto.getGrade())
                .phone(signupDto.getPhone())
                .build();
        semtleUserInfoRepository.save(userInfo);


        return SemtleUserRes.UserDetail.toDto(user, userInfo);
    }

    @Override
    public SemtleUserRes.UserDetail nowUser(String email) {
        SemtleUser user = semtleUserRepository.findByEmail(email).get();
        SemtleUserInfo userInfo = semtleUserInfoRepository.findByUserId(user.getUserId()).get();
        return SemtleUserRes.UserDetail.toDto(user, userInfo);
    }


    @Override
    public Token login(SemtleUserReq.LoginDto loginDto, String userAgent) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        String email = loginDto.getEmail();

        Token token = jwtProvider.createToken(email);
        //RefreshToken을 DB에 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .keyId(token.getKey())
                .refreshToken(token.getRefreshToken())
                .userAgent(userAgent).build();

        Optional<RefreshToken> tokenOptional = refreshTokenRepository.findByKeyId(email);

        //refreshToken이 있는지 검사
        if(tokenOptional.isEmpty()) {
            refreshTokenRepository.save(
                    RefreshToken.builder()
                            .keyId(token.getKey())
                            .refreshToken(token.getRefreshToken())
                            .userAgent(userAgent).build());
        }else {
            //refreshToken이 있으면, 업데이트
            refreshToken.update(tokenOptional.get().getRefreshToken(), tokenOptional.get().getUserAgent());

        }


        return token;
    }


}
