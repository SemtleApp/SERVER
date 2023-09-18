package com.semtleapp.semtleapp.semtlebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class BorrowSemtleBookReqDto {

    public BorrowSemtleBookReqDto(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    private Long bookId;
    private Long userId;

}
