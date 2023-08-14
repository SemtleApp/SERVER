package com.semtleapp.semtleapp.semtlebook.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookReqDto;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "semtle_book")
public class SemtleBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", unique = true, nullable = false)
    private Long bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_image")
    private String bookImage;

    @Column(name = "book_count")
    private int bookCount;

    @Column(name = "status")
    private String status;

    @Column(name = "user_id_v1")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private SemtleUser semtleUser;

    //변경 메소드
    public void change(UpdateSemtleBookReqDto updateSemtleBookReqDto) {
        this.bookName = updateSemtleBookReqDto.getBookName();
        this.bookAuthor = updateSemtleBookReqDto.getBookAuthor();
        this.bookImage = updateSemtleBookReqDto.getBookImage();
        this.bookCount = updateSemtleBookReqDto.getBookCount();
    }
}
