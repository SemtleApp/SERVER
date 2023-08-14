package com.semtleapp.semtleapp.semtlebook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@Table(name = "semtle_borrow")
public class SemtleBorrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id", unique = true, nullable = false)
    private Long borrowId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_Id")
    private String bookId;

}
