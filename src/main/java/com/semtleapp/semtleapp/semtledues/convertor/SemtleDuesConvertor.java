package com.semtleapp.semtleapp.semtledues.convertor;

import com.semtleapp.semtleapp.semtledues.dto.GetDuesResDto;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;

public class SemtleDuesConvertor {
    public static GetDuesResDto.DuesList getDues(SemtleUserInfo result) {
        return GetDuesResDto.DuesList.builder()
                .name(result.getName())
                .studentId(result.getStudentId())
                .status(result.getStatus())
                .build();
    }
}
