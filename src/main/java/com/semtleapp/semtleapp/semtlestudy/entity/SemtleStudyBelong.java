package com.semtleapp.semtleapp.semtlestudy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.semtleapp.semtleapp.global.entity.BaseTimeEntity;
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
@Table(name = "semtle_study_belong")
public class SemtleStudyBelong extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "belong_id", unique = true, nullable = false)
    private Long belongId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private SemtleUser semtleUser;

    @ManyToOne
    @JoinColumn(name = "study_id")
    @JsonBackReference
    private SemtleStudyRoom semtleStudyRoom;

//    @Builder
//    public SemtleStudyBelong(Long belongId, SemtleUser semtleUser, SemtleStudyRoom semtleStudyRoom) {
//        this.belongId = belongId;
//        this.semtleUser = semtleUser;
//        this.semtleStudyRoom = semtleStudyRoom;
//    }

}
