package com.semtleapp.semtleapp.semtlestudy.dto;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyStudyPostReqDto {

    private Long postId;
    private String title;
    private String content;
    private List<PhotoDto> photoDtoList;

}
