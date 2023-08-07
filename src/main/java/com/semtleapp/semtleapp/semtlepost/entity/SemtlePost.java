package com.semtleapp.semtleapp.semtlepost.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.semtleapp.semtleapp.global.entity.BaseTimeEntity;
import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "semtle_post")
public class SemtlePost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, nullable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private SemtleUser semtleUser;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

//    @Builder
//    public SemtlePost(Long postId, String title, String content, SemtleUser semtleUser) {
//        this.postId = postId;
//        this.title = title;
//        this.content = content;
//        this.semtleUser = semtleUser;
//    }

    public SemtlePostDto toDto() {
        return SemtlePostDto.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .semtleUserDto(semtleUser.toDto())
                .createdDate(getCreatedDate())
                .updatedDate(getUpdatedDate()).build();
    }

    public void setSemtleUser(SemtleUser semtleUser) {
        this.semtleUser = semtleUser;
    }


}
