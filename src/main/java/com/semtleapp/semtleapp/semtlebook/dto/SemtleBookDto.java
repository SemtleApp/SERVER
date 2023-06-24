package com.semtleapp.semtleapp.semtlebook.dto;

import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SemtleBookDto {
    private String bookName;
    private String bookAuthor;
    private String bookImage;
    private int bookCount;
    private String status;

    @Builder
    public SemtleBookDto(String bookName, String bookAuthor, String bookImage, int bookCount, String status) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
        this.bookCount = bookCount;
        this.status = status;
    }

    public SemtleBook toEntity() {
        return SemtleBook.builder()
                .bookName(bookName)
                .bookAuthor(bookAuthor)
                .bookImage(bookImage)
                .bookCount(bookCount)
                .status(status).build();
        }
}
