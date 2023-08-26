package com.semtleapp.semtleapp.semtleuser.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.semtleuser.dto.Token;
import com.semtleapp.semtleapp.semtleuser.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.semtleapp.semtleapp.global.exception.ErrorCode.FAILED_SIGNUP;

@RequiredArgsConstructor
@RestController
@Api(tags={"01.User❤️"})
@RequestMapping("/oauth")
public class AuthController {

    private final AuthService authService;


    /**
     * 카카오 소셜로그인
     * https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=03e358973f5106d2b7c655b68daf1862&redirect_uri=http://localhost:9000/oauth/kakao
     * https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=03e358973f5106d2b7c655b68daf1862&redirect_uri=http://semtle.catholic.ac.kr:9000/oauth/kakao
     * */
    @ApiOperation(value = "카카오 로그인 -> 이 API로 접속해 셈틀용 토큰을 반환",
            notes = "원래는 프론트엔드가 첫 회원가입 링크로 들어가서 code를 받고, 그 받은 코드로 이 api에 접근해서" +
                    "카카오의 access_token을 반환 후\n" +
                    "access_token을 /kakao/login의 요청에 넣어서 우리 사이트의 로그인하고 그 결과를 얻음\n")
    @GetMapping("/kakao")
    public ApiResponse<Token> getAccessTokenKakao(@RequestParam String code) {
        //카카오 서버 액세스토큰 받기
        String accessTokenFromSocial = authService.getKakaoAccessToken(code);
        System.out.println("code for kakaoServer : " + code);
        System.out.println("accessToken for kakaoServer : " + accessTokenFromSocial);

        //카카오 로그인 로직
        Token token = authService.createAndLoginKakaoUser(accessTokenFromSocial);

        if(token == null)
            throw new CustomException(FAILED_SIGNUP);

        return new ApiResponse<>(token);
    }

    @ApiOperation(value = "카카오 로그인", notes = "카카오 로그인")
    @PostMapping("/kakao/login")
    public Token kakaoSignupOrLogin(@RequestParam String accessTokenFromSocial) {
        Token token = authService.createAndLoginKakaoUser(accessTokenFromSocial);

        if(token == null)
            throw new CustomException(FAILED_SIGNUP);

        return token;
    }
}
