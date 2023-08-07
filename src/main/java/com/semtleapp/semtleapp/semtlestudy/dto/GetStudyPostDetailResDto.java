package com.semtleapp.semtleapp.semtlestudy.dto;

import com.semtleapp.semtleapp.file.entity.Photo;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class GetStudyPostDetailResDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PostDetail{

        private String roomName;
        private String title;
        private String nickName;
        private String content;
        private List<FileList> fileList;
        private LocalDate createdDate;

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
