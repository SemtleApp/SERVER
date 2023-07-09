package com.semtleapp.semtleapp.semtlestudy.service;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostResDto;
import com.semtleapp.semtleapp.semtlestudy.dto.SemtleStudyPostDto;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import com.semtleapp.semtleapp.semtlestudy.repository.SemtleStudyPostRepository;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SemtleStudyPostServiceImpl implements SemtleStudyPostService{

    private final SemtleStudyPostRepository semtleStudyPostRepository;
    private final FileUserService fileUserService;
    private final SemtleUserRepository semtleUserRepository;

    @Override
    public RegisterStudyPostResDto registerStudyPost(String email, RegisterStudyPostReqDto registerStudyPostReqDto, List<MultipartFile> files) {
        SemtleStudyPost semtleStudyPost = registerStudyPostReqDto.toEntity();
        semtleStudyPost.setSemtleUser(semtleUserRepository.findByEmail(email).get());
        SemtleStudyPostDto res_semtleStudyPostDto = semtleStudyPostRepository.save(semtleStudyPost).toDto();
        uploadPhoto(files, res_semtleStudyPostDto);
        return RegisterStudyPostResDto.builder().message("스터디글이 등록되었습니다").build();
    }

    private void uploadPhoto(List<MultipartFile> files, SemtleStudyPostDto res_semtleStudyPostDto) {
        if(files != null) {
            try {
                fileUserService.saveFile(files, PhotoType.STUDY, res_semtleStudyPostDto.getPostId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
