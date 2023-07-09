package com.semtleapp.semtleapp.semtlestudy.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostResDto;
import com.semtleapp.semtleapp.semtlestudy.service.SemtleStudyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/s1/es")
public class SemtleStudyController {

    private final SemtleStudyPostService semtleStudyPostService;

    @PostMapping("/register")
    public ApiResponse<RegisterStudyPostResDto> registerStudyPost(Principal principal, @RequestPart(value = "image", required = false) List<MultipartFile> files, @RequestPart(value = "registerStudyPostReqDto") RegisterStudyPostReqDto registerStudyPostReqDto) throws Exception {
        return new ApiResponse<>(semtleStudyPostService.registerStudyPost(principal.getName(), registerStudyPostReqDto, files));
    }

}
