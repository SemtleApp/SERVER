package com.semtleapp.semtleapp.semtlenotice.service;

import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.semtlenotice.convertor.SemtleNoticeConvertor;
import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeReq;
import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeRes;
import com.semtleapp.semtleapp.semtlenotice.entity.SemtleNotice;
import com.semtleapp.semtleapp.semtlenotice.repository.SemtleNoticeRepository;
import com.semtleapp.semtleapp.semtlestudy.convertor.SemtleStudyConvertor;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostResDto;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyRoom;
import com.semtleapp.semtleapp.semtlestudy.service.SemtleStudyService;
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
public class SemtleNoticeServiceImpl implements SemtleNoticeService {

    private final SemtleNoticeRepository semtleNoticeRepository;
    private final FileUserService fileUserService;
    private final SemtleUserRepository semtleUserRepository;

    @Override
    public SemtleNoticeRes.PostNoticeRes registerNoticePost(String email, SemtleNoticeReq.PostNoticeReq postNoticeReq, List<MultipartFile> files) {
        SemtleUser semtleUser = semtleUserRepository.findByEmail(email).get();
        SemtleNotice semtleNotice = SemtleNoticeConvertor.registerStudyNotice(semtleUser, postNoticeReq);
        Long postId = semtleNoticeRepository.save(semtleNotice).getPostId();
        if(files != null) {
            try {
                fileUserService.saveFile(files, PhotoType.NOTICE, postId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SemtleNoticeRes.PostNoticeRes.builder().message("공지글이 등록되었습니다").build();
    }



}