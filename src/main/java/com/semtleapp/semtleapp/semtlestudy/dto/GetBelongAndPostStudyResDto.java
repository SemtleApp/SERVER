package com.semtleapp.semtleapp.semtlestudy.dto;

import lombok.*;

import java.time.LocalDate;

public class GetBelongAndPostStudyResDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BelongStudyList {

        private String roomName;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StudyPostList {

        private String title;
        private String email; //원래는 닉네임이지만 임시용
        private LocalDate createdDate;
        private boolean isCheckFile;

    }

}
