package com.semtleapp.semtleapp.semtleuser.entity;

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
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id", unique = true, nullable = false)
    private Long refreshTokenId;

    @Column(name = "refresh_token", unique = true)
    private String refreshToken;

    @Column(name = "key_id")
    private String keyId;

    @Column(name = "user_agent")
    private String userAgent;

//    @Builder
//    public RefreshToken(String refreshToken, String keyId, String userAgent) {
//        this.refreshToken = refreshToken;
//        this.keyId = keyId;
//        this.userAgent = userAgent;
//    }

    public void update(String refreshToken, String userAgent) {
        this.refreshToken = refreshToken;
        this.userAgent = refreshToken;
    }
}
