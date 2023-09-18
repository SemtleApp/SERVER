package com.semtleapp.semtleapp.semtlebook.service;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.semtlebook.dto.*;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SemtleBookService {

    ApiResponse createBook(CreateSemtleBookDto semtleBookDto, List<MultipartFile> files);

    List<CreateSemtleBookDto> getBookList();

    void saveBookFile(List<MultipartFile> files, SemtleBook semtleBook);

    ApiResponse updateBook(UpdateSemtleBookReqDto updateSemtleBookReqDto, List<MultipartFile> files);

    ApiResponse deleteBook(DeleteSemtleBookReqDto deleteSemtleBookReqDto);

    ApiResponse borrowBook(BorrowSemtleBookReqDto borrowSemtleBookReqDto);

    ApiResponse bookDetail(SemtleBookDetailReqDto semtleBookDetailReqDto);

}
