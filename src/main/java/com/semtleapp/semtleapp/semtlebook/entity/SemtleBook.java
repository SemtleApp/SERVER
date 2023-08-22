package com.semtleapp.semtleapp.semtlebook.entity;

import com.semtleapp.semtleapp.semtlebook.dto.UpdateSemtleBookReqDto;
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

    @Column(name = "status")
    private String status;

    @Column(name = "book_count")
    private int bookCount;

    //변경 메소드
    public void change(UpdateSemtleBookReqDto updateSemtleBookReqDto) {
        this.bookName = updateSemtleBookReqDto.getBookName();
        this.bookAuthor = updateSemtleBookReqDto.getBookAuthor();
    }

    public  void changeStatus(String status){
        this.status = status;
    }

    public void increaseCount(){
        bookCount += 1;
    }

    public void decreaseCount(){
        bookCount -= 1;
    }

    public SemtleBook toEntity() {
        return SemtleBook.builder()
                .bookName(bookName)
                .bookAuthor(bookAuthor).build();
    }
}
