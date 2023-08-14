package com.semtleapp.semtleapp.semtlebook.controller;

import com.semtleapp.semtleapp.global.entity.CustomResponse;
import com.semtleapp.semtleapp.semtlebook.dto.CreateSemtleBookDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookReqDto;
import com.semtleapp.semtleapp.semtlebook.service.SemtleBookService;
import com.semtleapp.semtleapp.semtlebook.service.SemtleBookServiceImpl;
import com.semtleapp.semtleapp.semtlebook.service.SemtleBorrowService;
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
    private final SemtleBorrowService semtleBorrowService;


    @ApiOperation(value = "책 등록", notes = "책 등록")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM', 'BSEM', 'CSEM', 'DSEM')")
    @PostMapping("/upload/book")
    public CustomResponse uploadBook(@RequestPart(value = "image", required = false) List<MultipartFile> files, @RequestPart(value = "semtleBooktDto") CreateSemtleBookDto semtleBookDto) throws Exception {
        return new CustomResponse.ResponseMap(200, "data", semtleBookService.create(semtleBookDto, files));

    }

    @ApiOperation(value = "책 수정", notes = "책 수정")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM', 'BSEM', 'CSEM', 'DSEM')")
    @PostMapping("/update/book")
    public CustomResponse updateBook(@RequestPart(value = "image", required = false) List<MultipartFile> files, @RequestPart(value = "semtleBooktDto") UpdateSemtleBookReqDto updateSemtleBookReqDto) throws Exception {
        return new CustomResponse.ResponseMap(200, "data", semtleBookService.updateBook(updateSemtleBookReqDto, files));

    }
}
