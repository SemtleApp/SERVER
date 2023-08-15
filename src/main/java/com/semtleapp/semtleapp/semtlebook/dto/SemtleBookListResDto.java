package com.semtleapp.semtleapp.semtlebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class SemtleBookListResDto {
    private String bookName;
    private String bookAuthor;
    private int bookCount;

    public SemtleBookListResDto(String bookName, String bookAuthor, int bookCount){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookCount = bookCount;
    }
}
