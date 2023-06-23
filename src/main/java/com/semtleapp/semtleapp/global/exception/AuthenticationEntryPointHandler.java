package com.semtleapp.semtleapp.global.exception;

import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequestMapping
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");
        ErrorCode errorCode;

        if(exception == null) {
            errorCode = ErrorCode.NEED_TO_LOGIN;
            setResponse(response, errorCode);
            return;
        }

        if(exception.equals("NullPointerException")) {
            errorCode = ErrorCode.NEED_TO_LOGIN;
            setResponse(response, errorCode);
            return;
        }


        if(exception.equals("PasswordNotFoundException")) {
            errorCode = ErrorCode.PASSWORD_NOT_FOUND_EXCEPTION;
            setResponse(response, errorCode);
            return;
        }

        if(exception.equals("ForbiddenException")) {
            errorCode = ErrorCode.FORBIDDEN;
            setResponse(response, errorCode);
            return;
        }

        //토큰이 만료된 경우
        if(exception.equals("ExpiredJwtException")) {
            errorCode = ErrorCode.EXPIRED_JWT;
            setResponse(response, errorCode);
            return;
        }

        //아이디 비밀번호가 다를 경우
        if(exception.equals("UsernameOrPasswordNotFoundException")) {
            errorCode = ErrorCode.USERNAME_OR_PASSWORD_NOT_FOUND_EXCEPTION;
            setResponse(response, errorCode);
            return;
        }

    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        JSONObject json = new JSONObject();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        json.put("code", errorCode.getCode());
        json.put("message", errorCode.getMessage());
        response.getWriter().print(json);

    }
}
