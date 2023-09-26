package com.semtleapp.semtleapp.semtledues.service;

import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.semtledues.convertor.SemtleDuesConvertor;
import com.semtleapp.semtleapp.semtledues.dto.*;
import com.semtleapp.semtleapp.semtledues.entity.SemtleDuesReceiptPost;
import com.semtleapp.semtleapp.semtledues.repository.SemtleDuesPostRepository;
import com.semtleapp.semtleapp.semtledues.repository.SemtleDuesRepository;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SemtleDuesServiceImpl implements SemtleDuesService {

    private final SemtleDuesRepository semtleDuesRepository;
    private final SemtleDuesPostRepository semtleDuesPostRepository;
    private final FileUserService fileUserService;

    @Override
    public List<GetDuesResDto.DuesList> getDues() {
        List<SemtleUserInfo> semtleUserInfo = semtleDuesRepository.findAll();
        List<GetDuesResDto.DuesList> duesList = new ArrayList<>();
        semtleUserInfo.forEach(
                result -> duesList.add(
                        SemtleDuesConvertor.getDues(result)
                        )
                );

        return duesList;
    }

    @Override
    public ModifyDuesResDto modifyDuesStatus(String studentId, ModifyDuesReqDto modifyDuesReqDto){
        SemtleUserInfo semtleUserInfo = semtleDuesRepository.findByStudentId(studentId).get();
        semtleUserInfo.updateStatus(modifyDuesReqDto);

        return  ModifyDuesResDto.builder().message("회비 납부 상태가 수정되었습니다.").build();
    }

    @Override
    public ReceiptDuesPostResDto receiptDuesPost(ReceiptDuesPostReqDto receiptDuesPostReqDto, List<MultipartFile> files) {
        SemtleDuesReceiptPost semtleDuesReceiptPost = SemtleDuesConvertor.receiptDuesPost(receiptDuesPostReqDto);
        SemtleDuesReceiptPost saveSemtleDuesPost = semtleDuesPostRepository.save(semtleDuesReceiptPost);
        uploadPhotos(files, saveSemtleDuesPost);
        return ReceiptDuesPostResDto.builder().message("영수증이 업로드 되었습니다.").build();

    }

    private void uploadPhotos(List<MultipartFile> files, SemtleDuesReceiptPost saveSemtleDuesPost) {
        if(files != null) {
            try {
                fileUserService.saveFiles(files, PhotoType.DUES, saveSemtleDuesPost.getReceiptId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
