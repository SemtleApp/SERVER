package com.semtleapp.semtleapp.semtleuser.dto;

import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.*;

public class SemtleUserReq {


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class Login {
        private String email;
        //private String role;
        private String password;


        public SemtleUser toEntity() {
            return SemtleUser.builder()
                    .email(email)
                    .password(password)
                    .role("ROLE_USER")
                    .build();
        }

    }


}
