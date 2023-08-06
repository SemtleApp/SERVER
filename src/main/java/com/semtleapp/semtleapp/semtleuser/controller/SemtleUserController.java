package com.semtleapp.semtleapp.semtleuser.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.global.jwt.JwtService;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserReq;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserRes;
import com.semtleapp.semtleapp.semtleuser.dto.Token;
import com.semtleapp.semtleapp.semtleuser.service.SemtleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.semtleapp.semtleapp.global.exception.ErrorCode.BAD_REQUEST;

@RequiredArgsConstructor
@RestController
@Api(tags={"01.User❤️"})
@RequestMapping("/user")
public class SemtleUserController {
    private final SemtleUserService semtleUserService;

    private final JwtService jwtService;


    @ApiOperation(value = "회원가입", notes = "회원가입")
    @PostMapping("/signup")
    public ApiResponse<SemtleUserRes.UserDetail> signup(@RequestBody SemtleUserReq.SignupDto signupDto) {
        SemtleUserRes.UserDetail result = semtleUserService.signup(signupDto);
        return new ApiResponse<>(result);
    }

    @ApiOperation(value = "로그인", notes = "로그인")
    @PostMapping("/login")
    public ApiResponse<Token> login(HttpServletRequest request, @RequestBody SemtleUserReq.LoginDto loginDto,
                                    @RequestHeader(value = "User-Agent", required = false) String userAgent) {

        return new ApiResponse<>(semtleUserService.login(loginDto, userAgent));
    }

    @ApiOperation(value = "현재 유저 조회", notes = "현재 유저 조회, 쿼리스트링 입력 필요 없음")
    @GetMapping()
    public ApiResponse<SemtleUserRes.UserDetail> nowUser(Principal principal){
        //principal.getName() 하면 시큐리티에서 들어온 회원의 이메일을 가지고옴.
        //TODO 예외처리 추가 예정

        if(principal.getName() ==  null)
            throw new CustomException(BAD_REQUEST);

        return new ApiResponse<>(semtleUserService.nowUser(principal.getName()));
    }


    //TODO 리다이렉트 URL, 비즈니스 로직 수정
//
//    /**
//     * 카카오 소셜로그인
//     * https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=abf4119e38a436ab64718033228aad2d&redirect_uri=http://localhost:8080/oauth/kakao
//     * https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=abf4119e38a436ab64718033228aad2d&redirect_uri=http://43.200.23.72:8080/oauth/kakao
//     * */
//    @ApiOperation(value = "인가코드 캐치를 위한 api, 사용x, 백엔드 터미널로 반환중",
//            notes = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=abf4119e38a436ab64718033228aad2d&redirect_uri=http://localhost:8080/oauth/kakao" +
//                    "원래는 프론트엔드가 첫 회원가입 링크로 들어가서 code를 받고, 그 받은 코드로 이 api에 접근해서" +
//                    "카카오의 access_token을 반환 후" +
//                    "access_token을 /kakao/login의 요청에 넣어서 우리 사이트의 로그인하고 그 결과를 얻음")
//    @GetMapping("/kakao")
//    public ApiResponse<String> getAccessTokenKakao(@RequestParam String code) {
//        String accessToken=authService.getKakaoAccessToken(code);
//        System.out.println("code for kakaoServer : " + code);
//        System.out.println("for /oauth/kakao : " + accessToken);
//
//        return new ApiResponse<>(accessToken);
//    }
//
//    @ApiOperation(value = "카카오 로그인", notes = "카카오 로그인")
//    @PostMapping("/kakao/login")
//    public TokenRes kakaoSignupOrLogin(@RequestBody UserReq.SocialReq socialReq) {
//        TokenRes tokenRes = authService.createAndLoginKakaoUser(socialReq);
//
//        if(tokenRes == null)
//            throw new BadRequestException("사용자 정보가 없습니다");
//        return tokenRes;
//    }

}
