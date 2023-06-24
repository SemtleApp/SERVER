package com.semtleapp.semtleapp.semtlebook.service;

import com.semtleapp.semtleapp.semtlebook.dto.SemtleBookDto;

import java.util.List;

public interface SemtleBookService {

    SemtleBookDto create(SemtleBookDto semtleBookDto);

    List<SemtleBookDto> getBookList();
}
