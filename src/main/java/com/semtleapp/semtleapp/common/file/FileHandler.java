package com.semtleapp.semtleapp.common.file;

import com.semtleapp.semtleapp.common.entity.Photo;
import com.semtleapp.semtleapp.common.entity.PhotoDto;
import com.semtleapp.semtleapp.common.entity.PhotoType;
import com.semtleapp.semtleapp.common.repository.PhotoRepository;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileHandler {
    private final PhotoRepository photoRepository;

    private final Path fileLocation;
    public static final String URL_VIEW = "/image?name=";
    public static final String URL_DOWNLOAD = "/downloadFile/";

    @Autowired
    public FileHandler(PhotoRepository photoRepository, FileUploadProperties fileUploadProperties) {
        this.photoRepository = photoRepository;
        this.fileLocation = Paths.get(fileUploadProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PhotoDto uploadFile(MultipartFile file, PhotoType photoType, Long targetId) throws IOException {
        String originFileName = file.getOriginalFilename();
        String fileName = StringUtils.cleanPath(parseUUID(originFileName));

        if(fileName.contains(".."))
            throw new FileUploadException("File Name is Not Visible");
        Path targetLocation = Path.of(this.fileLocation.toString() + "/" + photoType.getPath() + "/" + fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        File FILE = new File(targetLocation.toString());
        long bytes = FILE.length() / 1024;

        Photo photo = Photo.builder()
                .targetId(targetId)
                .type(photoType.getValue())
                .uu_id(FILE.getName())
                .file_name(originFileName)
                .file_path(targetLocation.toString())
                .file_url(parseFileUrl(URL_VIEW, FILE.getName()))
                .file_download_path(parseFileUrl(URL_DOWNLOAD, FILE.getName()))
                .file_size(bytes).build();
        photoRepository.save(photo);
        return photo.toDto();
    }


    private String parseUUID(String fileName) {
        Date now = new Date();
        String[] name = fileName.split("\\.");
        StringBuffer sb = new StringBuffer(name[0]);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String uploadTime = sdf.format(now);

        String extension = fileName.substring(fileName.lastIndexOf("."));
        sb.append(uploadTime);
        sb.append(extension);

        return sb.toString();
    }

    private String parseFileUrl(String urlPath, String fileName) {
        StringBuffer sb = new StringBuffer(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString());
        sb.append(urlPath);
        sb.append(fileName);
        return sb.toString();
    }
}
