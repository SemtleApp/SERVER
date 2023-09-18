package com.semtleapp.semtleapp.semtlebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class DeleteSemtleBookReqDto {
    private Long bookId;

    public DeleteSemtleBookReqDto(Long bookId) {
        this.bookId = bookId;
    }
}
