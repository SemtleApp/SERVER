package com.semtleapp.semtleapp.semtlepost.dto;

import com.semtleapp.semtleapp.semtlepost.entity.SemtlePost;
import com.semtleapp.semtleapp.semtleuser.dto.SemtleUserDto;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class SemtlePostDto {
    private Long postId;
    private String title;
    private String content;
    private SemtleUserDto semtleUserDto;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Builder
    public SemtlePostDto(Long postId, String title, String content, SemtleUserDto semtleUserDto, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.semtleUserDto = semtleUserDto;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public SemtlePost toEntity() {
        return SemtlePost.builder()
                .postId(postId)
                .title(title)
                .content(content).build();
    }


}