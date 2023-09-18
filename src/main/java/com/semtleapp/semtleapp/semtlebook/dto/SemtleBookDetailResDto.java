package com.semtleapp.semtleapp.semtlebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class SemtleBookDetailResDto {
    private String bookName;
    private String bookAuthor;
    private int bookCount;
    private String bookStatus;

    public SemtleBookDetailResDto(String bookName, String bookAuthor, int bookCount, String bookStatus) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookCount = bookCount;
        this.bookStatus = bookStatus;
    }
}
