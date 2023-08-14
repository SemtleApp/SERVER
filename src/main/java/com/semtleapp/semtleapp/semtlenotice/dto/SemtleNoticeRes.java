package com.semtleapp.semtleapp.semtlenotice.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class SemtleNoticeRes {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PostNoticeRes {
        private String message;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class GetNoticeRes {
        private String title;
        private String content;
        private String nickname;
        private LocalDate createdDate;
        private LocalDate updatedDate;
        private boolean isCheckFile;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetNoticeDetailRes{
        private String title;
        private String content;
        private String nickName;
        private LocalDate createdDate;
        private LocalDate updatedDate;
        private List<SemtleNoticeRes.FileList> fileList;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileList{
        private String fileName;
        private String fileDownLoadPath;
    }



}
