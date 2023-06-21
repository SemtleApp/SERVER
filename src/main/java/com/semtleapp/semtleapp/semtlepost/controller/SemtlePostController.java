package com.semtleapp.semtleapp.semtlepost.controller;

import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import com.semtleapp.semtleapp.semtlepost.service.SemtlePostService;
import com.semtleapp.semtleapp.semtlepost.service.SemtlePostServiceImpl;
import com.semtleapp.semtleapp.semtleuser.dto.CustomResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/post")
public class SemtlePostController {
    private final SemtlePostService semtlePostService;

    public SemtlePostController(SemtlePostServiceImpl semtlePostService) {
        this.semtlePostService = semtlePostService;
    }

    @PostMapping("/write")
    public CustomResponse createPost(Principal principal, @RequestPart(value = "image", required = false) List<MultipartFile> files, @RequestPart(value = "semtlePostDto") SemtlePostDto semtlePostDto) throws Exception {
        //principal.getName() 하면 시큐리티에서 들어온 회원의 이매일을 가지고옴.
        return new CustomResponse.ResponseMap(200, "data", semtlePostService.create(principal.getName(), semtlePostDto, files));
    }

}
