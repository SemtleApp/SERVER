package com.semtleapp.semtleapp.semtlenotice.dto;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import lombok.*;

import java.util.List;

public class SemtleNoticeReq {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PostNoticeReq {
        private String title;
        private String content;
        private List<PhotoDto> photoDtoList;
    }



}
