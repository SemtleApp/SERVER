package com.semtleapp.semtleapp.semtlebook.service;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.file.service.FileUserServiceImpl;
import com.semtleapp.semtleapp.semtlebook.convertor.SemtleBookConvertor;
import com.semtleapp.semtleapp.semtlebook.dto.CreateSemtleBookDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookReqDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookResDto;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import com.semtleapp.semtleapp.semtlebook.repository.SemtleBookRepository;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class SemtleBookServiceImpl implements SemtleBookService {
    private final SemtleBookRepository semtleBookRepository;
    private final SemtleUserRepository semtleUserRepository;
    private final SemtleBookConvertor semtleBookConvertor;
    private final FileUserService fileUserService;

    //도서 등록
    @Override
    public CreateSemtleBookDto create(CreateSemtleBookDto createSemtleBookDto, List<MultipartFile> files) {
        SemtleBook semtleBook = createSemtleBookDto.toEntity();

        semtleBookRepository.save(semtleBook);

        saveBookFile(files, semtleBook);

        return createSemtleBookDto;
    }


    //도서 편집
    public UpdateSemtleBookResDto updateBook(UpdateSemtleBookReqDto updateSemtleBookReqDto, List<MultipartFile> files) {

        SemtleBook findSemtleBook =
                semtleBookRepository.findById(updateSemtleBookReqDto.getBookId()).orElse(null);

        findSemtleBook.change(updateSemtleBookReqDto);

        saveBookFile(files, findSemtleBook);

        UpdateSemtleBookResDto updateSemtleBookResDto= semtleBookConvertor.updateSemtleBookResDto(findSemtleBook);

        return updateSemtleBookResDto;

    }

    //도서 리스트
    @Override
    public List<CreateSemtleBookDto> getBookList() {

        List<SemtleBook> semtleBooks = semtleBookRepository.findAll();
        return null;
    }

    //도서 검색

    @Override
    public void saveBookFile(List<MultipartFile> files, SemtleBook semtleBook) {
        try {
            List<PhotoDto> photoDtoList = fileUserService.saveFile(files, PhotoType.BOOK, semtleBook.getBookId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
