package com.semtleapp.semtleapp.semtlebook.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.entity.CustomResponse;
import com.semtleapp.semtleapp.semtlebook.dto.*;
import com.semtleapp.semtleapp.semtlebook.service.SemtleBookService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class SemtleBookController {
    private final SemtleBookService semtleBookService;


    @ApiOperation(value = "책 등록", notes = "책 등록")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM', 'BSEM', 'CSEM', 'DSEM')")
    @PostMapping("/upload")
    public ApiResponse uploadBook(@RequestPart(value = "image", required = false) List<MultipartFile> files, @RequestPart(value = "semtleBooktDto") CreateSemtleBookDto semtleBookDto) throws Exception {
        return semtleBookService.createBook(semtleBookDto, files);
    }

    @ApiOperation(value = "책 상세정보", notes = "책 상세정보")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM', 'BSEM', 'CSEM', 'DSEM')")
    @PostMapping("/detail")
    public ApiResponse bookDetail(@RequestBody SemtleBookDetailReqDto semtleBookDetailReqDto) throws Exception {
        return semtleBookService.bookDetail(semtleBookDetailReqDto);

    }

    @ApiOperation(value = "책 수정", notes = "책 수정")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM', 'BSEM', 'CSEM', 'DSEM')")
    @PostMapping("/update")
    public ApiResponse updateBook(@RequestPart(value = "image", required = false) List<MultipartFile> files, @RequestPart(value = "semtleBooktDto") UpdateSemtleBookReqDto updateSemtleBookReqDto) throws Exception {
        return semtleBookService.updateBook(updateSemtleBookReqDto, files);

    }

    @ApiOperation(value = "책 삭제", notes = "책 삭제")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM', 'BSEM', 'CSEM', 'DSEM')")
    @PostMapping("/delete")
    public ApiResponse deleteBook(@RequestBody DeleteSemtleBookReqDto deleteSemtleBookReqDto) throws Exception {
        return semtleBookService.deleteBook(deleteSemtleBookReqDto);

    }

    @ApiOperation(value = "책 대여", notes = "책 대여")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM', 'BSEM', 'CSEM', 'DSEM')")
    @PostMapping("/borrow")
    public ApiResponse borrowBook(@RequestBody BorrowSemtleBookReqDto borrowSemtleBookReqDto) throws Exception {
        return semtleBookService.borrowBook(borrowSemtleBookReqDto);
    }

}
