package com.semtleapp.semtleapp.common.controller;

import com.semtleapp.semtleapp.common.service.FileService;
import com.semtleapp.semtleapp.common.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class FileController {
    private final FileService fileService;
    @Autowired
    public FileController(FileServiceImpl fileService) {
        this.fileService = fileService;
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
