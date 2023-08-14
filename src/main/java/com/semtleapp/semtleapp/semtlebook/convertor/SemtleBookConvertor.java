package com.semtleapp.semtleapp.semtlebook.convertor;

import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookReqDto;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookResDto;
import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import org.springframework.context.annotation.Bean;

public class SemtleBookConvertor {


    public UpdateSemtleBookResDto updateSemtleBookResDto(SemtleBook semtleBook) {
        return UpdateSemtleBookResDto.builder()
                .bookId(semtleBook.getBookId())
                .bookName(semtleBook.getBookName())
                .bookAuthor(semtleBook.getBookAuthor())
                .bookImage(semtleBook.getBookImage())
                .bookCount(semtleBook.getBookCount()).build();
    }


}
