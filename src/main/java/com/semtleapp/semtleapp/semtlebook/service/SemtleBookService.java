package com.semtleapp.semtleapp.semtlebook.service;

import com.semtleapp.semtleapp.semtlebook.dto.CreateSemtleBookDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookReqDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookResDto;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SemtleBookService {

    CreateSemtleBookDto create(CreateSemtleBookDto semtleBookDto, List<MultipartFile> files);

    List<CreateSemtleBookDto> getBookList();

    public void saveBookFile(List<MultipartFile> files, SemtleBook semtleBook);

    public UpdateSemtleBookResDto updateBook(UpdateSemtleBookReqDto updateSemtleBookReqDto, List<MultipartFile> files);
}
