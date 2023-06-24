package com.semtleapp.semtleapp.semtlebook.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.semtleapp.semtleapp.semtlebook.dto.SemtleBookDto;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public SemtleBook(Long bookId, String bookName, String bookAuthor, String bookImage, int bookCount, String status, SemtleUser semtleUser) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookImage = bookImage;
        this.bookCount = bookCount;
        this.status = status;
        this.semtleUser = semtleUser;
    }

    public SemtleBookDto toDto() {
        return SemtleBookDto.builder()
                .bookName(bookName)
                .bookAuthor(bookAuthor)
                .bookImage(bookImage)
                .bookCount(bookCount)
                .status(status).build();
    }
}
