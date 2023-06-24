package com.semtleapp.semtleapp.semtlebook.service;

import com.semtleapp.semtleapp.semtlebook.dto.SemtleBookDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SemtleBookService {

    SemtleBookDto create(String email, SemtleBookDto semtleBookDto, List<MultipartFile> files);

    List<SemtleBookDto> getBookList();
}
