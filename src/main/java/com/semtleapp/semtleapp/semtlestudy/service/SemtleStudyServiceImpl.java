package com.semtleapp.semtleapp.semtlestudy.service;

import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.semtlestudy.convertor.SemtleStudyConvertor;
import com.semtleapp.semtleapp.semtlestudy.dto.*;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyBelong;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyRoom;
import com.semtleapp.semtleapp.semtlestudy.repository.SemtleStudyBelongRepository;
import com.semtleapp.semtleapp.semtlestudy.repository.SemtleStudyPostRepository;
import com.semtleapp.semtleapp.semtlestudy.repository.SemtleStudyRoomRepository;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SemtleStudyServiceImpl implements SemtleStudyService {

    private final SemtleStudyPostRepository semtleStudyPostRepository;
    private final FileUserService fileUserService;
    private final SemtleUserRepository semtleUserRepository;
    private final SemtleStudyRoomRepository semtleStudyRoomRepository;
    private final SemtleStudyBelongRepository semtleStudyBelongRepository;

    @Override
    public RegisterStudyPostResDto registerStudyPost(String email, RegisterStudyPostReqDto registerStudyPostReqDto, List<MultipartFile> files) {
        SemtleUser semtleUser = semtleUserRepository.findByEmail(email).get();
        SemtleStudyRoom semtleStudyRoom = semtleStudyRoomRepository.findById(registerStudyPostReqDto.getStudyId()).get();
        SemtleStudyPost semtleStudyPost = SemtleStudyConvertor.registerStudyPost(semtleUser, semtleStudyRoom, registerStudyPostReqDto);
        SemtleStudyPost saveSemtleStudyPost = semtleStudyPostRepository.save(semtleStudyPost);
        uploadPhotos(files, saveSemtleStudyPost);
        return RegisterStudyPostResDto.builder().message("스터디글이 등록되었습니다").build();
    }

    @Override
    public RegisterStudyRoomResDto registerStudyRoom(String email, RegisterStudyRoomReqDto registerStudyRoomReqDto) {
        SemtleUser semtleUser = semtleUserRepository.findByEmail(email).get();
        SemtleStudyRoom semtleStudyRoom = SemtleStudyConvertor.registerStudyRoom(semtleUser, registerStudyRoomReqDto);
        semtleStudyRoomRepository.save(semtleStudyRoom);
        SemtleStudyBelong semtleStudyBelong = SemtleStudyConvertor.belongStudyRoom(semtleUser, semtleStudyRoom);
        semtleStudyBelongRepository.save(semtleStudyBelong);
        return RegisterStudyRoomResDto.builder().message("스터디룸이 생성되었습니다").build();
    }

    @Override
    public List<GetBelongAndPostStudyResDto.BelongStudyList> getBelongStudy(String email) {
        SemtleUser semtleUser = semtleUserRepository.findByEmail(email).get();
        List<GetBelongAndPostStudyResDto.BelongStudyList> belongList = new ArrayList<>();
        List<SemtleStudyRoomRepository.GetBelongStudyList> belongStudyLists = semtleStudyRoomRepository.getBelongStudy(semtleUser.getUserId());
        belongStudyLists.forEach(
                result -> belongList.add(
                        new GetBelongAndPostStudyResDto.BelongStudyList(
                                result.getRoomName()
                        )
                )
        );
        return belongList;
    }

    @Override
    public List<GetBelongAndPostStudyResDto.StudyPostList> getStudyPost(String roomName) {
        List<GetBelongAndPostStudyResDto.StudyPostList> postList = new ArrayList<>();
        List<SemtleStudyPostRepository.GetStudyPostList> studyPostLists = semtleStudyPostRepository.getStudyPost(roomName);
        studyPostLists.forEach(
                result -> postList.add(
                        new GetBelongAndPostStudyResDto.StudyPostList(
                                result.getTitle(),
                                result.getEmail(),
                                result.getCreatedDate(),
                                result.getIsFileCheck()
                        )
                )
        );
        return postList;
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
