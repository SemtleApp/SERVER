package com.semtleapp.semtleapp.semtlebook.entity;

import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.sql.Timestamp;

import static javax.persistence.FetchType.LAZY;

@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "semtle_borrow")
public class SemtleBorrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id", unique = true, nullable = false)
    private Long borrowId;

    @Column(name = "borrow_nickname")
    private String borrowNickname;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "book_id")
    private SemtleBook semtleBook;

    @Column(name = "borrow_time")
    private Timestamp borrowTime;

    public static SemtleBorrow toEntity(String borrowNickname, SemtleBook semtleBook ) {
        return SemtleBorrow.builder()
                .borrowNickname(borrowNickname)
                .semtleBook(semtleBook).build();
    }

    public void setBorrowNicknameo(String borrowNickname){
        this.borrowNickname = borrowNickname;
    }

    public void setSemtleBook(SemtleBook semtleBook){
        this.semtleBook = semtleBook;
    }

}
