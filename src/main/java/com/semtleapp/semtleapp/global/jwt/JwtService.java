package com.semtleapp.semtleapp.global.jwt;

import com.semtleapp.semtleapp.global.entity.CustomResponse;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.semtleuser.dto.Token;
import com.semtleapp.semtleapp.semtleuser.entity.RefreshToken;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.global.exception.ErrorCode;
import com.semtleapp.semtleapp.semtleuser.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@AllArgsConstructor
@Service
public class JwtService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

//    @Transactional
//    public Token login(HttpServletRequest request, SemtleUserDto semtleUserDto, String userAgent) {
//        CustomResponse.ResponseMap result = null;
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(semtleUserDto.getEmail(), semtleUserDto.getPassword())
//        );
//
//        return getLoginToken(semtleUserDto.getEmail(), userAgent);
//
//    }
//
//    private Token getLoginToken(String email, String userAgent) {
//        Token token = jwtProvider.createToken(email);
//        //RefreshToken을 DB에 저장
//        RefreshToken refreshToken = RefreshToken.builder()
//                .keyId(token.getKey())
//                .refreshToken(token.getRefreshToken())
//                .userAgent(userAgent).build();
//
//        Optional<RefreshToken> tokenOptional = refreshTokenRepository.findByKeyId(email);
//
//        //refreshToken이 있는지 검사
//        if(tokenOptional.isEmpty()) {
//            refreshTokenRepository.save(
//                    RefreshToken.builder()
//                            .keyId(token.getKey())
//                            .refreshToken(token.getRefreshToken())
//                            .userAgent(userAgent).build());
//        }else {
//            //refreshToken이 있으면, 업데이트
//            refreshToken.update(tokenOptional.get().getRefreshToken(), tokenOptional.get().getUserAgent());
//
//        }
//
//
//        return token;
//    }

    public String getNewAccessToken(RefreshToken refreshToken) {
        if(refreshToken.getRefreshToken() != null)
            return jwtProvider.validateRefreshToken(refreshToken);
        else
            throw new CustomException(ErrorCode.NEED_TO_RELOGIN);
    }

}
