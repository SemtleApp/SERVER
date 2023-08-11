package com.semtleapp.semtleapp.semtledues.dto;

import lombok.*;

public class GetIngResDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IngList {

        private String name;
        private String nickname;
        private int grade;
        private String studentId;
        private String phone;
        private String status;
    }
}
