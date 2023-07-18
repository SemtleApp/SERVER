package com.semtleapp.semtleapp.semtlestudy.dto;

import lombok.*;

public class GetBelongAndPostStudyResDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BelongStudyList{

        private String roomName;

    }

}
