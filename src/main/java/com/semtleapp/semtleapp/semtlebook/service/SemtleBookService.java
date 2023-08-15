package com.semtleapp.semtleapp.semtlebook.service;

import com.semtleapp.semtleapp.semtlebook.dto.CreateSemtleBookDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookReqDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookResDto;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SemtleBookService {

    CreateSemtleBookDto createBook(CreateSemtleBookDto semtleBookDto, List<MultipartFile> files);

    List<CreateSemtleBookDto> getBookList();

    void saveBookFile(List<MultipartFile> files, SemtleBook semtleBook);

    UpdateSemtleBookResDto updateBook(UpdateSemtleBookReqDto updateSemtleBookReqDto, List<MultipartFile> files);

    Long deleteBook(Long deleteBookId);
}
