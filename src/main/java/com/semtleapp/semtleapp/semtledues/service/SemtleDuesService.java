package com.semtleapp.semtleapp.semtledues.service;

import com.semtleapp.semtleapp.semtledues.dto.GetDuesResDto;
import com.semtleapp.semtleapp.semtledues.dto.ModifyDuesReqDto;
import com.semtleapp.semtleapp.semtledues.dto.ModifyDuesResDto;

import java.util.List;

public interface SemtleDuesService {

    List<GetDuesResDto.DuesList> getDues();

    ModifyDuesResDto modifyDuesStatus(String studentId, ModifyDuesReqDto modifyDuesReqDto);
}
