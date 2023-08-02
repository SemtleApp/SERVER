package com.semtleapp.semtleapp.semtlestudy.service;

import com.semtleapp.semtleapp.semtlestudy.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SemtleStudyService {

    RegisterStudyPostResDto registerStudyPost(String email, RegisterStudyPostReqDto registerStudyPostReqDto, List<MultipartFile> files);

    RegisterStudyRoomResDto registerStudyRoom(String email, RegisterStudyRoomReqDto registerStudyRoomReqDto);

    List<GetBelongAndPostStudyResDto.BelongStudyList> getBelongStudy(String email);

    List<GetBelongAndPostStudyResDto.StudyPostList> getStudyPost(String roomName);

    GetStudyPostDetailResDto.PostDetail getStudyPostDetail(Long postId);

    ModifyStudyPostResDto modifyStudyPost(String email, ModifyStudyPostReqDto modifyStudyPostReqDto, List<MultipartFile> files);

    DeleteStudyPostResDto deleteStudyPost(String email, Long postId);

}
