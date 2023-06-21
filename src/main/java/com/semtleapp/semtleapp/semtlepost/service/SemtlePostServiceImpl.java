package com.semtleapp.semtleapp.semtlepost.service;

import com.semtleapp.semtleapp.common.entity.PhotoDto;
import com.semtleapp.semtleapp.common.entity.PhotoType;
import com.semtleapp.semtleapp.common.file.FileHandler;
import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import com.semtleapp.semtleapp.semtlepost.entity.SemtlePost;
import com.semtleapp.semtleapp.semtlepost.repository.SemtlePostRepository;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SemtlePostServiceImpl implements SemtlePostService  {
    private final SemtlePostRepository semtlePostRepository;
    private final SemtleUserRepository semtleUserRepository;
    private final FileHandler fileHandler;

    @Override
    public SemtlePostDto create(String email, SemtlePostDto semtlePostDto, List<MultipartFile> files) {
        SemtlePost semtlePost = semtlePostDto.toEntity();
        semtlePost.setSemtleUser(semtleUserRepository.findByEmail(email).get());

        SemtlePostDto res_semtlePostDto = semtlePostRepository.save(semtlePost).toDto();

        List<PhotoDto> photoDtoList = new ArrayList<>();

        try {
            for(MultipartFile file : files) {
                photoDtoList.add(fileHandler.uploadFile(file, PhotoType.POST, res_semtlePostDto.getPostId()));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        res_semtlePostDto.setPhotoDtoList(photoDtoList);

        return res_semtlePostDto;
    }


}
