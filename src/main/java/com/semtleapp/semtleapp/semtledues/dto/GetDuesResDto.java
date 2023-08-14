package com.semtleapp.semtleapp.semtledues.dto;

import lombok.*;

public class GetDuesResDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DuesList {

        private String name;
        private String studentId;
        private String status;
    }
}
