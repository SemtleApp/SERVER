package com.semtleapp.semtleapp.semtlenotice.service;

import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.global.exception.ErrorCode;
import com.semtleapp.semtleapp.semtlenotice.convertor.SemtleNoticeConvertor;
import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeReq;
import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeRes;
import com.semtleapp.semtleapp.semtlenotice.entity.SemtleNotice;
import com.semtleapp.semtleapp.semtlenotice.repository.SemtleNoticeRepository;
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
        SemtleNotice semtleNotice = SemtleNoticeConvertor.registerNoticePost(semtleUser, postNoticeReq);
        Long postId = semtleNoticeRepository.save(semtleNotice).getPostId();
        uploadPhotos(files, postId);
        return SemtleNoticeRes.PostNoticeRes.builder().message("공지글이 등록되었습니다").build();
    }

    @Override
    public SemtleNoticeRes.PostNoticeRes modifyNoticePost(String email, SemtleNoticeReq.PatchNoticeReq patchNoticeReq, List<MultipartFile> files) {
        SemtleUser semtleUser = semtleUserRepository.findByEmail(email).get();
        SemtleNotice semtleNotice = semtleNoticeRepository.findById(patchNoticeReq.getPostId()).get();
        if(semtleUser.getUserId() == semtleNotice.getSemtleUser().getUserId()){
            SemtleNotice saveSemtleNotice = semtleNoticeRepository.save(SemtleNoticeConvertor.modifyNoticePost(semtleUser, patchNoticeReq));
            fileUserService.deleteFile(PhotoType.NOTICE, saveSemtleNotice.getPostId());
            uploadPhotos(files, saveSemtleNotice.getPostId());
        } else {
            throw new CustomException(ErrorCode.FORBIDDEN);
        }
        return SemtleNoticeRes.PostNoticeRes.builder().message("공지글이 수정되었습니다").build();
    }


    private void uploadPhotos(List<MultipartFile> files, Long postId) {
        if(files != null) {
            try {
                fileUserService.saveFile(files, PhotoType.NOTICE, postId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}