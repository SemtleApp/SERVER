package com.semtleapp.semtleapp.semtlestudy.service;

import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.semtlestudy.convertor.SemtleStudyPostConvertor;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostResDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyRoomReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyRoomResDto;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyRoom;
import com.semtleapp.semtleapp.semtlestudy.repository.SemtleStudyPostRepository;
import com.semtleapp.semtleapp.semtlestudy.repository.SemtleStudyRoomRepository;
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
    private final SemtleStudyRoomRepository semtleStudyRoomRepository;

    @Override
    public RegisterStudyPostResDto registerStudyPost(String email, RegisterStudyPostReqDto registerStudyPostReqDto, List<MultipartFile> files) {
        SemtleUser semtleUser = semtleUserRepository.findByEmail(email).get();
        SemtleStudyPost semtleStudyPost = SemtleStudyPostConvertor.registerStudyPost(semtleUser, registerStudyPostReqDto);
        SemtleStudyPost saveSemtleStudyPost = semtleStudyPostRepository.save(semtleStudyPost);
        uploadPhotos(files, saveSemtleStudyPost);
        return RegisterStudyPostResDto.builder().message("스터디글이 등록되었습니다").build();
    }

    @Override
    public RegisterStudyRoomResDto registerStudyRoom(String email, RegisterStudyRoomReqDto registerStudyRoomReqDto) {
        SemtleUser semtleUser = semtleUserRepository.findByEmail(email).get();
        SemtleStudyRoom semtleStudyRoom = SemtleStudyPostConvertor.registerStudyRoom(semtleUser, registerStudyRoomReqDto);
        semtleStudyRoomRepository.save(semtleStudyRoom);
        return RegisterStudyRoomResDto.builder().message("스터디룸이 생성되었습니다").build();
    }

    private void uploadPhotos(List<MultipartFile> files, SemtleStudyPost saveSemtleStudyPost) {
        if(files != null) {
            try {
                fileUserService.saveFile(files, PhotoType.STUDY, saveSemtleStudyPost.getPostId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
