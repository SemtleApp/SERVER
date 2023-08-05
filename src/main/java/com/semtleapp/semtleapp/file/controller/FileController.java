package com.semtleapp.semtleapp.file.controller;

import com.semtleapp.semtleapp.file.service.FileService;
import com.semtleapp.semtleapp.file.service.FileServiceImpl;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.file.service.FileUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class FileController {
    private final FileService fileService;
    private final FileUserService fileUserService;

    @Autowired
    public FileController(FileServiceImpl fileService, FileUserServiceImpl fileUserService) {
        this.fileService = fileService;
        this.fileUserService = fileUserService;
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request, @PathVariable String fileName) {
        return fileService.loadFileAsResource(request, fileName);
    }

    @GetMapping("/image")
    public ResponseEntity<Resource> getImage(@RequestParam Map<String, String> param) {
        return fileService.showImage(param);
    }


}
