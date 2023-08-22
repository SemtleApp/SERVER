package com.semtleapp.semtleapp.semtlebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class SemtleBookListResDto {
    private String bookName;
    private String bookAuthor;
    private int bookCount;
    private String status;
    private int dDay;
    private List<Integer> user = new ArrayList<>();

    public SemtleBookListResDto(String bookName, String bookAuthor, int bookCount, String status, int dDay, List<Integer> user) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookCount = bookCount;
        this.status = status;
        this.dDay = dDay;
        this.user = user;
    }
}
