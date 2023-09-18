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
public class CreateSemtleBookDto {
    private String bookName;
    private String bookAuthor;
    private int bookCount;

    public CreateSemtleBookDto(String bookName, String bookAuthor, int bookCount){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookCount = bookCount;
    }


    public SemtleBook toEntity() {
        return SemtleBook.builder()
                .bookName(bookName)
                .bookAuthor(bookAuthor)
                .bookCount(bookCount).build();
        }
}
