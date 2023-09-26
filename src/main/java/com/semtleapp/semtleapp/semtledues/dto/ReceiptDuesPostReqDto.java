package com.semtleapp.semtleapp.semtledues.dto;

import com.semtleapp.semtleapp.file.entity.PhotoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDuesPostReqDto {
    private String memo;
    private List<PhotoDto> photoDtoList;
}
