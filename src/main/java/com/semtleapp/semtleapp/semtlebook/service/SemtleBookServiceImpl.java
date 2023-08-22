package com.semtleapp.semtleapp.semtlebook.service;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import com.semtleapp.semtleapp.file.entity.PhotoType;
import com.semtleapp.semtleapp.file.service.FileUserService;
import com.semtleapp.semtleapp.global.entity.ApiResponse;
import com.semtleapp.semtleapp.global.exception.CustomException;
import com.semtleapp.semtleapp.global.exception.ErrorCode;
import com.semtleapp.semtleapp.semtlebook.convertor.SemtleBookConvertor;
import com.semtleapp.semtleapp.semtlebook.dto.*;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBorrow;
import com.semtleapp.semtleapp.semtlebook.repository.SemtleBookRepository;
import com.semtleapp.semtleapp.semtlebook.repository.SemtleBorrowRepository;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import com.semtleapp.semtleapp.semtleuser.repository.SemtleUserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
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
    private final SemtleUserInfoRepository semtleUserInfoRepository;
    private final SemtleBorrowRepository semtleBorrowRepository;
    private final FileUserService fileUserService;

    //도서 등록
    @Override
    public ApiResponse createBook(CreateSemtleBookDto createSemtleBookDto, List<MultipartFile> files) {
        SemtleBook semtleBook = createSemtleBookDto.toEntity();
        try{
            semtleBookRepository.save(semtleBook);
        }catch (DataIntegrityViolationException e) {
            throw new CustomException(ErrorCode.DUPLICATE_BOOK);
        }catch (JpaSystemException e){
            throw new CustomException(ErrorCode.NULL_BOOK);
        }

        saveBookFile(files, semtleBook);

        return new ApiResponse(createSemtleBookDto);
    }

    //도서 상세 정보
    @Override
    public ApiResponse bookDetail(SemtleBookDetailReqDto semtleBookDetailReqDto){
        SemtleBook semtleBook = semtleBookRepository.findById(semtleBookDetailReqDto.getBookId())
                .orElseThrow(()->new CustomException(ErrorCode.NOT_EXIST_BOOK));

        SemtleBookDetailResDto semtleBookDetailResDto = SemtleBookConvertor.semtleBookDetailResDto(semtleBook);

        return new ApiResponse(semtleBookDetailResDto);
    }


    //도서 편집
    public ApiResponse updateBook(UpdateSemtleBookReqDto updateSemtleBookReqDto, List<MultipartFile> files) {

        SemtleBook findSemtleBook =
                semtleBookRepository.findBybookName(updateSemtleBookReqDto.getBookName()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXIST_BOOK));

        findSemtleBook.change(updateSemtleBookReqDto);

        saveBookFile(files, findSemtleBook);

        UpdateSemtleBookResDto updateSemtleBookResDto= SemtleBookConvertor.updateSemtleBookResDto(findSemtleBook);

        return new ApiResponse(updateSemtleBookResDto);

    }

    //도서 리스트
    //미완성
    @Override
    public List<CreateSemtleBookDto> getBookList() {
        List<SemtleBook> semtleBooks = semtleBookRepository.findAll();
        return null;
    }

    //도서 삭제
    @Override
    public ApiResponse deleteBook(DeleteSemtleBookReqDto deleteSemtleBookReqDto){
        try{
            semtleBookRepository.deleteById(deleteSemtleBookReqDto.getBookId());
        }catch(EmptyResultDataAccessException e){
            throw new CustomException(ErrorCode.NOT_EXIST_BOOK);
        }
        return new ApiResponse(deleteSemtleBookReqDto);
    }

    //책 대여
    @Override
    public ApiResponse borrowBook(BorrowSemtleBookReqDto borrowSemtleBookReqDto){

        SemtleBook semtleBook = semtleBookRepository.findById(borrowSemtleBookReqDto.getBookId())
                .orElseThrow(()->new CustomException(ErrorCode.NOT_EXIST_BOOK));
        SemtleUserInfo semtleUserInfo = semtleUserInfoRepository.findById(borrowSemtleBookReqDto.getUserId())
                .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND));

        SemtleBorrow semtleBorrow = SemtleBorrow.toEntity(semtleUserInfo.getNickname(),semtleBook);

        semtleBorrow.setSemtleBook(semtleBook);
        semtleBorrow.setBorrowNicknameo(semtleUserInfo.getNickname());

        semtleBorrowRepository.save(semtleBorrow);

        return new ApiResponse(borrowSemtleBookReqDto);
    }

    //미완성
    public ApiResponse returnBook(ReturnSemtleBookDto returnSemtleBookDto){
        try{
            semtleBorrowRepository.deleteById(returnSemtleBookDto.getBookId());
        }catch(EmptyResultDataAccessException e){
            throw new CustomException(ErrorCode.NOT_EXIST_BOOK);
        }

        return new ApiResponse(returnSemtleBookDto);
    }


    @Override
    public void saveBookFile(List<MultipartFile> files, SemtleBook semtleBook) {
        try {
            List<PhotoDto> photoDtoList = fileUserService.saveFiles(files, PhotoType.BOOK, semtleBook.getBookId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
