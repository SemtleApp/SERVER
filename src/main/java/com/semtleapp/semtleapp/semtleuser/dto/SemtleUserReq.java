package com.semtleapp.semtleapp.semtleuser.dto;

import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.*;

public class SemtleUserReq {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class SignupDto {
        private String email; //아이디
        private String password;
        private String name;
        private String nickname;
        private int grade;
        private String studentId;
        private String phone;
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class LoginDto {
        private String email;
        private String password;
    }




}
