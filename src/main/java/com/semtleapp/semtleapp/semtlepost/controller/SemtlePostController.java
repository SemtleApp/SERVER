package com.semtleapp.semtleapp.semtlepost.controller;

import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import com.semtleapp.semtleapp.semtlepost.service.SemtlePostService;
import com.semtleapp.semtleapp.semtlepost.service.SemtlePostServiceImpl;
import com.semtleapp.semtleapp.semtleuser.dto.CustomResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/post")
public class SemtlePostController {
    private final SemtlePostService semtlePostService;

    public SemtlePostController(SemtlePostServiceImpl semtlePostService) {
        this.semtlePostService = semtlePostService;
    }

    @PostMapping("/write")
    public CustomResponse createPost(Principal principal, @RequestBody SemtlePostDto semtlePostDto) {
        //principal.getName() 하면 시큐리티에서 들어온 회원의 이매일을 가지고옴.
        return new CustomResponse.ResponseMap(200, "data", semtlePostService.create(principal.getName(), semtlePostDto));
    }

}
