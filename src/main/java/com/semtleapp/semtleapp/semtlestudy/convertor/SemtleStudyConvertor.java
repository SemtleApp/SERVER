package com.semtleapp.semtleapp.semtlestudy.convertor;

import com.semtleapp.semtleapp.semtlestudy.dto.GetStudyPostDetailResDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyRoomReqDto;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyBelong;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyRoom;
import com.semtleapp.semtleapp.semtlestudy.repository.SemtleStudyPostRepository;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;

import java.util.List;
import java.util.stream.Collectors;

public class SemtleStudyConvertor {

    public static SemtleStudyPost registerStudyPost(SemtleUser semtleUser, SemtleStudyRoom semtleStudyRoom, RegisterStudyPostReqDto registerStudyPostReqDto) {
        return SemtleStudyPost.builder()
                .title(registerStudyPostReqDto.getTitle())
                .content(registerStudyPostReqDto.getContent())
                .semtleUser(semtleUser)
                .semtleStudyRoom(semtleStudyRoom)
                .build();
    }

    public static SemtleStudyRoom registerStudyRoom(SemtleUser semtleUser, RegisterStudyRoomReqDto registerStudyRoomReqDto) {
        return SemtleStudyRoom.builder()
                .roomName(registerStudyRoomReqDto.getRoomName())
                .semtleUser(semtleUser)
                .build();
    }

    public static SemtleStudyBelong belongStudyRoom(SemtleUser semtleUser, SemtleStudyRoom semtleStudyRoom) {
        return SemtleStudyBelong.builder()
                .semtleUser(semtleUser)
                .semtleStudyRoom(semtleStudyRoom)
                .build();
    }

    public static GetStudyPostDetailResDto.PostDetail getfiles(SemtleStudyPostRepository.GetStudyPost studyPostDetail, List<SemtleStudyPostRepository.GetFileList> files) {
        List<GetStudyPostDetailResDto.FileList> fileList = files.stream().map(result -> new GetStudyPostDetailResDto.FileList(
                        result.getFileName(),
                        result.getFileDownLoadPath()
                ))
                .collect(Collectors.toList());

        return GetStudyPostDetailResDto.PostDetail.builder()
                .roomName(studyPostDetail.getRoomName())
                .title(studyPostDetail.getTitle())
                .content(studyPostDetail.getContent())
                .nickName(studyPostDetail.getNickName())
                .createdDate(studyPostDetail.getCreatedDate())
                .fileList(fileList)
                .build();
    }
}
