package com.semtleapp.semtleapp.semtleuser.service;

import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.global.jwt.JwtProvider;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
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
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.semtleapp.semtleapp.global.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class SemtleUserServiceImpl implements SemtleUserService {
    private final SemtleUserRepository semtleUserRepository;
    private final SemtleUserInfoRepository  semtleUserInfoRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    private final FileUserService fileUserService;

//    private void uploadPhoto(List<MultipartFile> files, Long userId) {
//        if(files != null) {
//            try {
//                fileUserService.saveFiles(files, PhotoType.USER, userId);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @SneakyThrows
    @Transactional
    @Override
    public SemtleUserRes.UserDetail signup(SemtleUserReq.SignupDto signupDto, MultipartFile file ) {
        semtleUserRepository.findByEmail(signupDto.getEmail()).ifPresent(e -> {
            throw new CustomException(ErrorCode.REGISTERED_EMAIL);
        });

        SemtleUser user = SemtleUser.builder()
                .email(signupDto.getEmail())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .role("ROLE_USER")
                .social("")
                .build();
        Long userId = semtleUserRepository.save(user).getUserId();

        SemtleUserInfo userInfo = SemtleUserInfo.builder()
                .semtleUser(user)
                .name(signupDto.getName())
                .nickname(signupDto.getNickname())
                .grade(signupDto.getGrade())
                .studentId(signupDto.getStudentId())
                .phone(signupDto.getPhone())
                .status("BEFORE") //첫 회원가입 시
                .build();
        semtleUserInfoRepository.save(userInfo);


        //프로필 사진 업로드하기
        fileUserService.saveFile(file, PhotoType.USER, userId);
        SemtleUserInfoRepository.GetProfileImage profileImage = semtleUserInfoRepository.getProfileImage(userId);

        return SemtleUserRes.UserDetail.toDto(user, userInfo, profileImage);
    }

    @Override
    public SemtleUserRes.UserDetail nowUser(String email) {
        SemtleUser user = semtleUserRepository.findByEmail(email).get();
        SemtleUserInfo userInfo = semtleUserInfoRepository.findBySemtleUser(user).get();
        SemtleUserInfoRepository.GetProfileImage profileImage = semtleUserInfoRepository.getProfileImage(user.getUserId());

        return SemtleUserRes.UserDetail.toDto(user, userInfo, profileImage);
    }


    @Override
    public Token login(SemtleUserReq.LoginDto loginDto, String userAgent) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
//        );
        SemtleUser semtleUser = semtleUserRepository.findByEmail(loginDto.getEmail()).orElse(null);


        //유저 존재 여부
        if(semtleUser == null)
            throw new CustomException(NOT_EXIST_USER);

        //비밀번호 일치 여부
        if(!passwordEncoder.matches(loginDto.getPassword(), semtleUser.getPassword())){
            throw new CustomException(PASSWORD_NOT_FOUND_EXCEPTION);
        }

        //관리자 승인 여부
        SemtleUserInfo semtleUserInfo = semtleUserInfoRepository.findBySemtleUser(semtleUser).get();
        if(semtleUserInfo.getStatus().equals("BEFORE"))
            throw new CustomException(UNAUTHORIZED_MEMBER);

        String email = loginDto.getEmail();

        Token token = jwtProvider.createToken(email);
        //RefreshToken을 DB에 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .keyId(token.getKey())
                .refreshToken(token.getRefreshToken())
                .userAgent(userAgent)
                .build();

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
