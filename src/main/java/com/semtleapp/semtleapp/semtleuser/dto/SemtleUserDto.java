package com.semtleapp.semtleapp.semtleuser.dto;


import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import com.semtleapp.semtleapp.semtlepost.entity.SemtlePost;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.cfg.AvailableSettings.USER;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SemtleUserDto {
    private String email;
    //private String role;
    private String password;


    public SemtleUser toEntity() {
        return SemtleUser.builder()
                .email(email)
                .password(password)
                .role("ROLE_USER")
                .build();
    }

}
