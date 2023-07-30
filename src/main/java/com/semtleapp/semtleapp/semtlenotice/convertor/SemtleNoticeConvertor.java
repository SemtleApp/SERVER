package com.semtleapp.semtleapp.semtlenotice.convertor;

import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeReq;
import com.semtleapp.semtleapp.semtlenotice.entity.SemtleNotice;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyRoomReqDto;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyBelong;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyRoom;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;

public class SemtleNoticeConvertor {

    public static SemtleNotice registerStudyNotice(SemtleUser semtleUser, SemtleNoticeReq.PostNoticeReq postNoticeReq) {
        return SemtleNotice.builder()
                .semtleUser(semtleUser)
                .title(postNoticeReq.getTitle())
                .content(postNoticeReq.getContent())
                .build();
    }


}
