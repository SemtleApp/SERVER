package com.semtleapp.semtleapp.semtlenotice.dto;

import lombok.*;

public class SemtleNoticeRes {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PostNoticeRes {
        private String message;
    }
}
