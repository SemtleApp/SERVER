package com.semtleapp.semtleapp.semtlestudy.dto;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SemtleStudyPostDto {

    private Long postId;
    private String title;
    private String content;
    private SemtleUserDto semtleUserDto;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<PhotoDto> photoDtoList;

    @Builder
    public SemtleStudyPostDto(Long postId, String title, String content, SemtleUserDto semtleUserDto, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.semtleUserDto = semtleUserDto;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public SemtleStudyPost toEntity() {
        return SemtleStudyPost.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .build();
    }

}
