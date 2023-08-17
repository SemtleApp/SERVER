package com.semtleapp.semtleapp.semtlebook.service;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.file.service.FileUserServiceImpl;
import com.semtleapp.semtleapp.semtlebook.dto.SemtleBookDto;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import com.semtleapp.semtleapp.semtlebook.repository.SemtleBookRepository;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class SemtleBookServiceImpl implements SemtleBookService {
    private final SemtleBookRepository semtleBookRepository;
    private final SemtleUserRepository semtleUserRepository;
    private final FileUserService fileUserService;

    public SemtleBookServiceImpl(SemtleBookRepository semtleBookRepository, SemtleUserRepository semtleUserRepository, FileUserServiceImpl fileUserService) {
        this.semtleBookRepository = semtleBookRepository;
        this.semtleUserRepository = semtleUserRepository;
        this.fileUserService = fileUserService;
    }

    @Override
    public SemtleBookDto create(String email, SemtleBookDto semtleBookDto, List<MultipartFile> files) {
        SemtleBook semtleBook = semtleBookDto.toEntity();
        semtleBook.setSemtleUser(semtleUserRepository.findByEmail(email).get());

        SemtleBookDto res_semtleBookDto = semtleBookRepository.save(semtleBook).toDto();

        try {
            List<PhotoDto> photoDtoList = fileUserService.saveFiles(files, PhotoType.BOOK, res_semtleBookDto.getBookId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<SemtleBookDto> getBookList() {
        return null;
    }
}
