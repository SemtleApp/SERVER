package com.semtleapp.semtleapp.file.service;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import com.semtleapp.semtleapp.file.entity.PhotoType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUserService {
    List<PhotoDto> saveFiles(List<MultipartFile> files, PhotoType photoType, Long targetId) throws IOException;

    PhotoDto saveFile(MultipartFile file, PhotoType photoType, Long targetId) throws IOException;
    void deleteFile(PhotoType photoType, Long targetId);

}
