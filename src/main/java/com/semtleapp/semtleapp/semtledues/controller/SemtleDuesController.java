package com.semtleapp.semtleapp.semtledues.controller;

import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.semtledues.dto.*;
import com.semtleapp.semtleapp.semtledues.service.SemtleDuesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags={"04.Dues💚"})
@RequestMapping("/dues")
public class SemtleDuesController {

    private final SemtleDuesService semtleDuesService;

    @ApiOperation(value = "회비 납부여부 전체조회", notes = "회비 납부여부 전체조회")
    @PreAuthorize("hasAnyRole('ADMIN', 'ASEM', 'BSEM' , 'CSEM')")
    @GetMapping("/dues")
    public ApiResponse<List<GetDuesResDto.DuesList>> getDues() throws Exception {
        return new ApiResponse<>(semtleDuesService.getDues());
    }

    @ApiOperation(value = "회비 납부상태 수정", notes = "회비 납부상태 수정")
    @PreAuthorize("hasAnyRole('ADMIN', 'ASEM', 'BSEM', 'CSEM')")
    @PatchMapping("/modify/{studentId}")
    public ApiResponse<ModifyDuesResDto> modifyDuesStatus(@PathVariable String studentId, @RequestBody ModifyDuesReqDto modifyDuesReqDto) throws Exception{
        return new ApiResponse<>(semtleDuesService.modifyDuesStatus(studentId, modifyDuesReqDto));
    }

    @ApiOperation(value = "영수증 업로드", notes = "영수증 업로드")
    @PreAuthorize("hasAnyRole('ADMIN', 'ASEM', 'BSEM', 'CSEM')")
    @PostMapping("/receipt")
    public ApiResponse<ReceiptDuesPostResDto> receiptDuesPost(@RequestPart(value = "file", required = false)List<MultipartFile> files,
                                                              @RequestPart(value = "receiptDuesPostReqDto")ReceiptDuesPostReqDto receiptDuesPostReqDto) throws Exception {
        return new ApiResponse<>(semtleDuesService.receiptDuesPost(receiptDuesPostReqDto, files));
    }
}
