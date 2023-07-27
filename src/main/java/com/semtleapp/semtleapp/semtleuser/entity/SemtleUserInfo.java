package com.semtleapp.semtleapp.semtleuser.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "semtle_user_info")
public class SemtleUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id", unique = true, nullable = false)
    private Long infoId;

    @Column(name = "user_id")
    private Long userId;

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


}
