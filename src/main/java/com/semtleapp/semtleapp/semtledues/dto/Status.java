package com.semtleapp.semtleapp.semtledues.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    BEFORE("BEFORE"), // 회원가입 완료한 인원
    ING("ING"), // 서기가 회원을 확인한 인원
    COMPLETE("COMPLETE"); // 회비 납부를 완료한 인원

    private String value;

    @JsonCreator
    public static Status from(String s) { return Status.valueOf(s.toUpperCase());}
}
