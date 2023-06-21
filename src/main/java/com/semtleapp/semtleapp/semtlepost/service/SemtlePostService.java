package com.semtleapp.semtleapp.semtlepost.service;

import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SemtlePostService {

    SemtlePostDto create(String email, SemtlePostDto semtlePostDto, List<MultipartFile> files);
}
