package com.semtleapp.semtleapp.semtledues.service;

import com.semtleapp.semtleapp.semtledues.dto.GetDuesResDto;

import java.util.List;

public interface SemtleDuesService {

    List<GetDuesResDto.DuesList> getDues();
}
