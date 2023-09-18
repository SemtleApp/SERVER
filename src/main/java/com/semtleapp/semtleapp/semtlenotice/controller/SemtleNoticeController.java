package com.semtleapp.semtleapp.semtlenotice.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.global.jwt.JwtService;
import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeReq;
import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeRes;
import com.semtleapp.semtleapp.semtlenotice.service.SemtleNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;


@RequiredArgsConstructor
@RestController
@Api(tags={"03.Notice💛"})
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

    @ApiOperation(value = "공지사항 글수정", notes = "공지사항 글수정")
    @PreAuthorize("hasAnyRole('ADMIN','ASEM','DSEM')")
    @PatchMapping
    public ApiResponse<SemtleNoticeRes.PostNoticeRes> modifyNoticePost(Principal principal, @RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "patchNoticeReq") SemtleNoticeReq.PatchNoticeReq patchNoticeReq) throws Exception {
        try{
            return new ApiResponse<>(semtleNoticeService.modifyNoticePost(principal.getName(), patchNoticeReq, files));
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @ApiOperation(value = "공지사항 글삭제", notes = "공지사항 글삭제")
    @PreAuthorize("hasAnyRole('ADMIN','ASEM','DSEM')")
    @DeleteMapping("/{postId}")
    public ApiResponse<SemtleNoticeRes.PostNoticeRes> deleteNoticePost(Principal principal, @PathVariable(name = "postId") Long postId) throws Exception {
        try{
            return new ApiResponse<>(semtleNoticeService.deleteNoticePost(principal.getName(), postId));
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @ApiOperation(value = "공지사항 글 전체조회", notes = "공지사항 글 전체조회")
    @GetMapping
    public ApiResponse<List<SemtleNoticeRes.GetNoticeRes>> getNoticePost() throws Exception {
        try{
            return new ApiResponse<>(semtleNoticeService.getNoticePost());
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @ApiOperation(value = "공지사항 글 상세조회", notes = "공지사항 글 상세조회")
    @GetMapping("/{postId}")
    public ApiResponse<SemtleNoticeRes.GetNoticeDetailRes> getNoticePostDetail(@PathVariable Long postId) throws Exception {
        try{
            return new ApiResponse<>(semtleNoticeService.getNoticePostDetail(postId));
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
