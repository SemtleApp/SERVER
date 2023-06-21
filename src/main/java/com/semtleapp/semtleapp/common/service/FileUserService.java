package com.semtleapp.semtleapp.common.service;

import com.semtleapp.semtleapp.common.entity.PhotoDto;
import com.semtleapp.semtleapp.common.entity.PhotoType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUserService {
    PhotoDto uploadFile(MultipartFile file, PhotoType photoType, Long targetId) throws IOException;
    void deleteFile(PhotoType photoType, Long targetId);

}
