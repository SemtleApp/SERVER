package com.semtleapp.semtleapp.semtledues.convertor;

import com.semtleapp.semtleapp.semtledues.dto.GetDuesResDto;
import com.semtleapp.semtleapp.semtledues.dto.ReceiptDuesPostReqDto;
import com.semtleapp.semtleapp.semtledues.entity.SemtleDuesReceiptPost;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;

public class SemtleDuesConvertor {
    public static GetDuesResDto.DuesList getDues(SemtleUserInfo result) {
        return GetDuesResDto.DuesList.builder()
                .name(result.getName())
                .studentId(result.getStudentId())
                .status(result.getStatus())
                .build();
    }

    public static SemtleDuesReceiptPost receiptDuesPost(ReceiptDuesPostReqDto receiptDuesPostReqDto) {
        return SemtleDuesReceiptPost.builder()
                .memo(receiptDuesPostReqDto.getMemo())
                .build();
    }
}
