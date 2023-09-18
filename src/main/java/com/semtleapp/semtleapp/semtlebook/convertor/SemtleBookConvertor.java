package com.semtleapp.semtleapp.semtlebook.convertor;

import com.semtleapp.semtleapp.semtlebook.dto.SemtleBookDetailResDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookReqDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookResDto;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import org.springframework.context.annotation.Bean;


public class SemtleBookConvertor {


    public static UpdateSemtleBookResDto updateSemtleBookResDto(SemtleBook semtleBook) {
        return UpdateSemtleBookResDto.builder()
                .bookId(semtleBook.getBookId())
                .bookName(semtleBook.getBookName())
                .bookAuthor(semtleBook.getBookAuthor())
                .bookCount(semtleBook.getBookCount()).build();
    }

    public static SemtleBookDetailResDto semtleBookDetailResDto(SemtleBook semtleBook){
        return SemtleBookDetailResDto.builder()
                .bookName(semtleBook.getBookName())
                .bookAuthor(semtleBook.getBookAuthor())
                .bookCount(semtleBook.getBookCount())
                .bookStatus(semtleBook.getStatus()).build();
    }


}
