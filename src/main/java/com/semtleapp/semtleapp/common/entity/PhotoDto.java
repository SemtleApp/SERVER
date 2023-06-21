package com.semtleapp.semtleapp.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@NoArgsConstructor
public class PhotoDto {
    private Long targetId;
    private String type;
    private String file_name;
    private String file_url;
    private String file_download_path;
    private Long file_size;

    @Builder
    public PhotoDto(Long targetId, String type, String file_name, String file_url, String file_download_path, Long file_size) {
        this.targetId = targetId;
        this.type = type;
        this.file_name = file_name;
        this.file_url = file_url;
        this.file_download_path = file_download_path;
        this.file_size = file_size;
    }


}
