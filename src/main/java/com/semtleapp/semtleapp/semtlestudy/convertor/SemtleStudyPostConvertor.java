package com.semtleapp.semtleapp.semtlestudy.convertor;

import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;

public class SemtleStudyPostConvertor {

    public static SemtleStudyPost registerStudyPost(SemtleUser semtleUser, RegisterStudyPostReqDto registerStudyPostReqDto) {
        return SemtleStudyPost.builder()
                .title(registerStudyPostReqDto.getTitle())
                .content(registerStudyPostReqDto.getContent())
                .semtleUser(semtleUser)
                .build();
    }

}
