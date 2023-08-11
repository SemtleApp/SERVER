package com.semtleapp.semtleapp.semtledues.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "semtle_user_info")
public class SemtleDues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id", unique = true, nullable = false)
    private Long infoId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private SemtleUser semtleUser;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "grade")
    private int grade;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private String status;
}
