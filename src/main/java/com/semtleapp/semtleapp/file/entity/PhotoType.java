package com.semtleapp.semtleapp.file.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.yaml.snakeyaml.util.EnumUtils;

@AllArgsConstructor
@Getter
public enum PhotoType {
    PROFILE("PROFILE", "/PROFILE"),
    POST("POST", "/POST"),
    NOTICE("NOTICE", "/NOTICE"),
    ACCOUNTING("ACCOUNTING", "/ACCOUNTING"),
    STUDY("STUDY", "/STUDY"),
    BOOK("BOOK", "/BOOK"),
    ANY("ANY", "/ANY"),
    USER("USER", "/USER"),

    ;

    private String value;
    private String path;


}
