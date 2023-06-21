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
    private String password;
    @Builder
    public SemtleUserDto(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public SemtleUser toEntity() {
        return SemtleUser.builder()
                .email(email)
                .password(password)
                .role(role).build();
    }

}
