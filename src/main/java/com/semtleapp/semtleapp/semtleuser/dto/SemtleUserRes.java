package com.semtleapp.semtleapp.semtleuser.dto;

import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserInfoRepository;
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
        private SemtleUserInfoRepository.GetProfileImage profileImage;


        public static UserDetail toDto(SemtleUser user, SemtleUserInfo userInfo, SemtleUserInfoRepository.GetProfileImage profileImage){
            return UserDetail.builder()
                    .email(user.getEmail())
                    .name(userInfo.getName())
                    .nickname(userInfo.getNickname())
                    .grade(userInfo.getGrade())
                    .studentId(userInfo.getStudentId())
                    .phone(userInfo.getPhone())
                    .profileImage(profileImage)
                    .build();
        }

    }





}



