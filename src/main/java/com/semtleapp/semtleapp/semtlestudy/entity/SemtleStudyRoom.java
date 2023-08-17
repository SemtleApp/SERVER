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
@Table(name = "semtle_studyRoom")
public class SemtleStudyRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id", unique = true, nullable = false)
    private Long studyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private SemtleUser semtleUser;

    @Column(name = "roomName")
    private String roomName;

    @Builder
    public SemtleStudyRoom(Long studyId, String roomName, SemtleUser semtleUser) {
        this.studyId = studyId;
        this.roomName = roomName;
        this.semtleUser = semtleUser;
    }

}
