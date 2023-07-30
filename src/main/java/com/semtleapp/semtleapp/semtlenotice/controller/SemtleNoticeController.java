package com.semtleapp.semtleapp.semtlenotice.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.global.jwt.JwtService;
import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeReq;
import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeRes;
import com.semtleapp.semtleapp.semtlenotice.service.SemtleNoticeService;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@RestController
@Api(tags={"03.Notice❤"})
@RequestMapping("/notice")
public class SemtleNoticeController {

    private final SemtleNoticeService semtleNoticeService;

    @ApiOperation(value = "공지사항 글작성", notes = "공지사항 글작성")
    @PreAuthorize("hasAnyRole('ADMIN','ASEM','DSEM')")
    @PostMapping
    public ApiResponse<SemtleNoticeRes.PostNoticeRes> registerNoticePost(Principal principal, @RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "postNoticeReq") SemtleNoticeReq.PostNoticeReq postNoticeReq) throws Exception {
        try{
            return new ApiResponse<>(semtleNoticeService.registerNoticePost(principal.getName(), postNoticeReq, files));
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
