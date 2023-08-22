package com.semtleapp.semtleapp.semtlebook.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class SemtleBookDetailReqDto {
    private Long BookId;

    public SemtleBookDetailReqDto(Long bookId) {
        BookId = bookId;
    }
}
