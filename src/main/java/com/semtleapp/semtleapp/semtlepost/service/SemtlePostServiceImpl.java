package com.semtleapp.semtleapp.semtlepost.service;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.file.service.FileUserServiceImpl;
import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import com.semtleapp.semtleapp.semtlepost.entity.SemtlePost;
import com.semtleapp.semtleapp.semtlepost.repository.SemtlePostRepository;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class SemtlePostServiceImpl implements SemtlePostService  {
    private final SemtlePostRepository semtlePostRepository;
    private final SemtleUserRepository semtleUserRepository;
    private final FileUserService fileUserService;
    @Autowired
    public SemtlePostServiceImpl(SemtlePostRepository semtlePostRepository, SemtleUserRepository semtleUserRepository, FileUserServiceImpl fileUserService) {
        this.semtlePostRepository = semtlePostRepository;
        this.semtleUserRepository = semtleUserRepository;
        this.fileUserService = fileUserService;
    }

    @Override
    public SemtlePostDto create(String email, SemtlePostDto semtlePostDto, List<MultipartFile> files) {
        SemtlePost semtlePost = semtlePostDto.toEntity();
        semtlePost.setSemtleUser(semtleUserRepository.findByEmail(email).get());

        SemtlePostDto res_semtlePostDto = semtlePostRepository.save(semtlePost).toDto();

        //이거 메서드 추출하는게 나을 듯
        if(files != null) {
            try {
                List<PhotoDto> photoDtoList = fileUserService.saveFile(files, PhotoType.POST, res_semtlePostDto.getPostId());
                res_semtlePostDto.setPhotoDtoList(photoDtoList);
            } catch (IOException e) {
                e.printStackTrace();
                //handlering Please
            }
        }


        return res_semtlePostDto;
    }


}
