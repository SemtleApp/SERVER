package com.semtleapp.semtleapp.semtlestudy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.semtleapp.semtleapp.global.entity.BaseTimeEntity;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "semtle_study")
public class SemtleStudyPost extends BaseTimeEntity {

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

    @Builder
    public SemtleStudyPost(Long postId, String title, String content, SemtleUser semtleUser) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.semtleUser = semtleUser;
    }

}