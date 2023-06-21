package com.semtleapp.semtleapp.common.service;

import com.semtleapp.semtleapp.common.entity.PhotoDto;
import com.semtleapp.semtleapp.common.entity.PhotoType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUserService {
    List<PhotoDto> saveFile(List<MultipartFile> files, PhotoType photoType, Long targetId) throws IOException;
    void deleteFile(PhotoType photoType, Long targetId);

}
