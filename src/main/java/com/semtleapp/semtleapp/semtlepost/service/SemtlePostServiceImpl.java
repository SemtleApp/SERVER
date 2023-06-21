package com.semtleapp.semtleapp.semtlepost.service;

import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import com.semtleapp.semtleapp.semtlepost.entity.SemtlePost;
import com.semtleapp.semtleapp.semtlepost.repository.SemtlePostRepository;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Service
public class SemtlePostServiceImpl implements SemtlePostService  {
    private final SemtlePostRepository semtlePostRepository;
    private final SemtleUserRepository semtleUserRepository;

    @Override
    public SemtlePostDto create(String email, SemtlePostDto semtlePostDto) {
        SemtlePost semtlePost = semtlePostRepository.save(semtlePostDto.toEntity());
        semtlePost.setSemtleUser(semtleUserRepository.findByEmail(email).get());
        return semtlePost.toDto();
    }
}
