package com.semtleapp.semtleapp.semtlestudy.dto;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStudyPostReqDto {

    private Long studyId;
    private String title;
    private String content;
    private List<PhotoDto> photoDtoList;

}