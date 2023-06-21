package com.semtleapp.semtleapp.semtleuser.dto;


import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import com.semtleapp.semtleapp.semtlepost.entity.SemtlePost;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class SemtleUserDto {
    private String email;
    private String role;
    private List<SemtlePostDto> semtlePostList;

    @Builder
    public SemtleUserDto(String email, String role, List<SemtlePostDto> semtlePostList) {
        this.email = email;
        this.role = role;
        this.semtlePostList = semtlePostList;
    }

    public SemtleUser toEntity() {
        return SemtleUser.builder()
                .email(email)
                .role(role)
                .semtlePostList(semtlePostList.stream().map(m -> m.toEntity()).collect(Collectors.toList())).build();
    }

}
