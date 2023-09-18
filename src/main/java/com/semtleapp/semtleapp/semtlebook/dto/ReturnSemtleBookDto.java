package com.semtleapp.semtleapp.semtlebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class ReturnSemtleBookDto {
    private Long bookId;

    public ReturnSemtleBookDto(Long bookId) {
        this.bookId = bookId;
    }
}
