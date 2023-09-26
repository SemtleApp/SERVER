package com.semtleapp.semtleapp.semtledues.service;

import com.semtleapp.semtleapp.semtledues.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SemtleDuesService {

    List<GetDuesResDto.DuesList> getDues();

    ModifyDuesResDto modifyDuesStatus(String studentId, ModifyDuesReqDto modifyDuesReqDto);

    ReceiptDuesPostResDto receiptDuesPost(ReceiptDuesPostReqDto receiptDuesPostReqDto, List<MultipartFile> files);
}
