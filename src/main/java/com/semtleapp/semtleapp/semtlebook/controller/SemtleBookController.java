package com.semtleapp.semtleapp.semtlebook.controller;

import com.semtleapp.semtleapp.global.entity.CustomResponse;
import com.semtleapp.semtleapp.semtlebook.dto.SemtleBookDto;
import com.semtleapp.semtleapp.semtlebook.service.SemtleBookService;
import com.semtleapp.semtleapp.semtlebook.service.SemtleBookServiceImpl;
import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/book")
public class SemtleBookController {
    private final SemtleBookService semtleBookService;

    public SemtleBookController(SemtleBookServiceImpl semtleBookService) {
        this.semtleBookService = semtleBookService;
    }

    @PostMapping("/upload/book")
    public CustomResponse uploadBook(Principal principal, @RequestPart(value = "image", required = false) List<MultipartFile> files, @RequestPart(value = "semtleBooktDto") SemtleBookDto semtleBookDto) throws Exception {
        return new CustomResponse.ResponseMap(200, "data", semtleBookService.create(principal.getName(), semtleBookDto, files));

    }
}
