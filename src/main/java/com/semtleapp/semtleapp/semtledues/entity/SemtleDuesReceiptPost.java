package com.semtleapp.semtleapp.semtledues.entity;

import com.semtleapp.semtleapp.global.entity.BaseTimeEntity;
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
@Table(name = "semtle_dues_receipt")
public class SemtleDuesReceiptPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_id", unique = true, nullable = false)
    private Long receiptId;

    @Column(name = "memo")
    private String memo;
}
