package com.semtleapp.semtleapp.semtleuser.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    ASEM("ROLE_ASEM"),
    BSEM("ROLE_BSEM"),
    CSEM("ROLE_CSEM"),
    DSEM("ROLE_DSEM"),
    ESEM("ROLE_ESEM"),
    USER("ROLE_USER");
    private String value;

    @JsonCreator
    public static Role from(String s) {
        return Role.valueOf(s.toUpperCase());
    }
}