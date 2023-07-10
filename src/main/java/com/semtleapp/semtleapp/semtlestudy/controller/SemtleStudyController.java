package com.semtleapp.semtleapp.semtlestudy.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostResDto;
import com.semtleapp.semtleapp.semtlestudy.service.SemtleStudyPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@Api(tags={"02.Study❤️"})
@RequestMapping("/study")
public class SemtleStudyController {

    private final SemtleStudyPostService semtleStudyPostService;

    @ApiOperation(value = "스터디룸 글작성", notes = "스터디룸 글작성")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM', 'BSEM', 'CSEM', 'DSEM')")
    @PostMapping("/register")
    public ApiResponse<RegisterStudyPostResDto> registerStudyPost(Principal principal, @RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "registerStudyPostReqDto") RegisterStudyPostReqDto registerStudyPostReqDto) throws Exception {
        return new ApiResponse<>(semtleStudyPostService.registerStudyPost(principal.getName(), registerStudyPostReqDto, files));
    }

}
