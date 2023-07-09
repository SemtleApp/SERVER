package com.semtleapp.semtleapp.semtlestudy.service;

import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostReqDto;
import com.semtleapp.semtleapp.semtlestudy.dto.RegisterStudyPostResDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SemtleStudyPostService {

    RegisterStudyPostResDto registerStudyPost(String email, RegisterStudyPostReqDto registerStudyPostReqDto, List<MultipartFile> files);

}
