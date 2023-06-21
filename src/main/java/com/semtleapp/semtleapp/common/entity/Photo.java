package com.semtleapp.semtleapp.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "photo")
public class Photo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id", unique = true, nullable = false)
    private Long photoId;

    @Column(name = "target_id")
    private Long targetId;

    @Column
    private String type;

    @Column
    private String uu_id;

    @Column
    private String file_name;

    @Column
    private String file_path;

    @Column
    private String file_url;

    @Column
    private String file_download_path;

    @Column
    private Long file_size;

    @Builder
    public Photo(Long targetId, String type, String uu_id, String file_name, String file_path, String file_url, String file_download_path, Long file_size) {
        this.targetId = targetId;
        this.type = type;
        this.uu_id = uu_id;
        this.file_name = file_name;
        this.file_path = file_path;
        this.file_url = file_url;
        this.file_download_path = file_download_path;
        this.file_size = file_size;
    }

    public PhotoDto toDto() {
        return PhotoDto.builder()
                .targetId(targetId)
                .type(type)
                .file_name(file_name)
                .file_url(file_url)
                .file_download_path(file_download_path)
                .file_size(file_size).build();
    }

}
