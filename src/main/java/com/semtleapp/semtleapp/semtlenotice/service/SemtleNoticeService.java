package com.semtleapp.semtleapp.semtlenotice.service;

import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeReq;
import com.semtleapp.semtleapp.semtlenotice.dto.SemtleNoticeRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SemtleNoticeService {
    SemtleNoticeRes.PostNoticeRes registerNoticePost(String email, SemtleNoticeReq.PostNoticeReq postNoticeReq, List<MultipartFile> files);
}
