package com.semtleapp.semtleapp.common.entity;

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
    ANY("ANY", "/ANY");

    private String value;
    private String path;


}
