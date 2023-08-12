package com.semtleapp.semtleapp.semtledues.convertor;

import com.semtleapp.semtleapp.semtledues.dto.GetIngResDto;
import com.semtleapp.semtleapp.semtledues.entity.SemtleDues;

public class SemtleDuesConvertor {
    public static GetIngResDto.IngList creatIng(SemtleDues result) {
        return GetIngResDto.IngList.builder()
                .name(result.getName())
                .nickname(result.getNickname())
                .grade(result.getGrade())
                .studentId(result.getStudentId())
                .phone(result.getPhone())
                .status(result.getStatus())
                .build();
    }
}
