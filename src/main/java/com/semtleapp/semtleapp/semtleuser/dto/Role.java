package com.semtleapp.semtleapp.semtleuser.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"), //회장
    ASEM("ROLE_ASEM"), //부회장
    BSEM("ROLE_BSEM"), //총무
    CSEM("ROLE_CSEM"), //서기
    DSEM("ROLE_DSEM"), //홍보
    ESEM("ROLE_ESEM"), //스터디
    USER("ROLE_USER"); //일반 유저
    private String value;

    @JsonCreator
    public static Role from(String s) {
        return Role.valueOf(s.toUpperCase());
    }
}