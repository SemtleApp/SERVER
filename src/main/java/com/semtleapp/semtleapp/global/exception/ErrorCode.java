package com.semtleapp.semtleapp.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public enum ErrorCode {

    //기본 ERROR
    BAD_REQUEST(400, "잘못된 요청입니다", HttpStatus.BAD_REQUEST),
    FORBIDDEN(403, "해당 요청에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN),

    NOT_FOUND(404, "일치하는 정보가 없습니다.", HttpStatus.NOT_FOUND),


    //커스텀 ERROR CODE
    USERNAME_OR_PASSWORD_NOT_FOUND_EXCEPTION(400, "아이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_FOUND_EXCEPTION(400, "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    NEED_TO_LOGIN(400, "로그인 후 이용가능합니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_JWT(400, "기존 토큰이 만료되었습니다. 해당 토큰을 가지고 /token/refresh 링크로 이동 후 토큰을 재발급 받으세요.", HttpStatus.UNAUTHORIZED),
    NEED_TO_RELOGIN(400, "모든 토큰이 만료되었습니다. 다시 로그인해주세요.", HttpStatus.UNAUTHORIZED),

    FAILED_SIGNUP(400, "회원가입에 실패하였습니다.", HttpStatus.BAD_REQUEST),

    DUPLICATE_EMAIL(400, "중복된 이메일입니다.", HttpStatus.BAD_REQUEST),
    REGISTERED_EMAIL(400, "등록된 회원입니다.", HttpStatus.BAD_REQUEST),

    NOT_EXIST_POST(400, "존재하지 않는 게시글입니다", HttpStatus.BAD_REQUEST),

    ;

    private int code;
    private String message;
    private HttpStatus status;


    public String toString() {
        return "{" +
                "\"code\" : " + "\""+code+"\"" +
                "\"status\" : " + "\""+status+"\"" +
                "\"message\" : " + "\""+message+"\"" +
                "}";
    }
}
