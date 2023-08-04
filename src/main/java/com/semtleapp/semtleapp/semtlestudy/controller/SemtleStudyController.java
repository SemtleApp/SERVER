package com.semtleapp.semtleapp.semtlestudy.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.semtlestudy.dto.*;
import com.semtleapp.semtleapp.semtlestudy.service.SemtleStudyService;
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
@Api(tags={"02.Study🧡"})
@RequestMapping("/study")
public class SemtleStudyController {

    private final SemtleStudyService semtleStudyService;

    @ApiOperation(value = "스터디룸 글작성", notes = "스터디룸 글작성")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM', 'BSEM', 'CSEM', 'DSEM')")
    @PostMapping("/register")
    public ApiResponse<RegisterStudyPostResDto> registerStudyPost(Principal principal, @RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "registerStudyPostReqDto") RegisterStudyPostReqDto registerStudyPostReqDto) throws Exception {
        return new ApiResponse<>(semtleStudyService.registerStudyPost(principal.getName(), registerStudyPostReqDto, files));
    }

    @ApiOperation(value = "스터디룸 생성", notes = "스터디룸 생성")
    @PreAuthorize("hasAnyRole('ESEM', 'ADMIN', 'ASEM')")
    @PostMapping("/room")
    public ApiResponse<RegisterStudyRoomResDto> registerStudyRoom(Principal principal, @RequestBody RegisterStudyRoomReqDto registerStudyRoomReqDto) {
        return new ApiResponse<>(semtleStudyService.registerStudyRoom(principal.getName(), registerStudyRoomReqDto));
    }

    @ApiOperation(value = "소속 스터디룸 조회", notes = "소속 스터디룸 조회")
    @GetMapping("/belong")
    public ApiResponse<List<GetBelongAndPostStudyResDto.BelongStudyList>> getBelongStudy(Principal principal) throws Exception {
        return new ApiResponse<>(semtleStudyService.getBelongStudy(principal.getName()));
    }

    @ApiOperation(value = "스터디룸 게시글 전체 조회", notes = "소속 스터디룸 게시글 전체조회")
    @GetMapping("/posts")
    public ApiResponse<List<GetBelongAndPostStudyResDto.StudyPostList>> getStudyPost(@RequestParam String roomName) throws Exception {
        return new ApiResponse<>(semtleStudyService.getStudyPost(roomName));
    }

    @ApiOperation(value = "스터디룸 게시글 상세조회", notes = "소속 스터디룸 게시글 상세조회")
    @GetMapping("/post/{postId}")
    public ApiResponse<GetStudyPostDetailResDto.PostDetail> getStudyPostDetail(@PathVariable Long postId) throws Exception {
        return new ApiResponse<>(semtleStudyService.getStudyPostDetail(postId));
    }

    @ApiOperation(value = "스터디룸 게시글 수정", notes = "스터디룸 게시글 수정")
    @PatchMapping("/modify")
    public ApiResponse<ModifyStudyPostResDto> modifyStudyPost(Principal principal, @RequestPart(value = "file", required = false) List<MultipartFile> files, @RequestPart(value = "modifyStudyPostReqDto") ModifyStudyPostReqDto modifyStudyPostReqDto) throws Exception {
        return new ApiResponse<>(semtleStudyService.modifyStudyPost(principal.getName(), modifyStudyPostReqDto, files));
    }

    @ApiOperation(value = "스터디룸 게시글 삭제", notes = "스터디룸 게시글 삭제")
    @DeleteMapping("/post/{postId}")
    public ApiResponse<DeleteStudyPostResDto> deleteStudyPost(Principal principal, @PathVariable Long postId) throws Exception {
        return new ApiResponse<>(semtleStudyService.deleteStudyPost(principal.getName(), postId));
    }

}
