package com.semtleapp.semtleapp.semtlebook.dto;

import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class UpdateSemtleBookResDto {
    private Long bookId;
    private String bookName;
    private String bookAuthor;
    private String bookImage;
    private int bookCount;

    public UpdateSemtleBookResDto(Long bookId, String bookName, String bookAuthor, String bookImage, int bookCount){
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
        this.bookCount = bookCount;
    }


}
