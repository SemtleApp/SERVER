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
    @ApiOperation(value = "인가코드 캐치를 위한 api, 사용x, 백엔드 터미널로 반환중",
            notes = "원래는 프론트엔드가 첫 회원가입 링크로 들어가서 code를 받고, 그 받은 코드로 이 api에 접근해서" +
                    "카카오의 access_token을 반환 후" +
                    "access_token을 /kakao/login의 요청에 넣어서 우리 사이트의 로그인하고 그 결과를 얻음")
    @GetMapping("/kakao")
    public ApiResponse<String> getAccessTokenKakao(@RequestParam String code) {
        String accessToken=authService.getKakaoAccessToken(code);
        System.out.println("code for kakaoServer : " + code);
        System.out.println("accessToken for kakaoServer : " + accessToken);

        return new ApiResponse<>(accessToken);
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
