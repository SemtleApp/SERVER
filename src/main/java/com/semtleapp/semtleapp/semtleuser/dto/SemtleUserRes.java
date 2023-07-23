package com.semtleapp.semtleapp.semtleuser.dto;

import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import lombok.*;

public class SemtleUserRes {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserDetail {
        private String email;
        private String name;
        private String nickname;
        private int grade;
        private String studentId;
        private String phone;

        public static UserDetail toDto(SemtleUser user, SemtleUserInfo userInfo){
            return UserDetail.builder()
                    .email(user.getEmail())
                    .name(userInfo.getName())
                    .nickname(userInfo.getNickname())
                    .grade(userInfo.getGrade())
                    .studentId(userInfo.getStudentId())
                    .phone(userInfo.getPhone())
                    .build();
        }

    }





}



