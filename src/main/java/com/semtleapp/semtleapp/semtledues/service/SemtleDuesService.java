package com.semtleapp.semtleapp.semtledues.service;

import com.semtleapp.semtleapp.semtledues.dto.GetIngResDto;

import java.util.List;

public interface SemtleDuesService {

    List<GetIngResDto.IngList> getIng(String status);
}
