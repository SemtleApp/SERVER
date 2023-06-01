package com.semtleapp.semtleapp.semtleuser.repository;


import com.semtleapp.semtleapp.semtleuser.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKeyId(String keyId);
    void deleteByKeyId(String keyId);
}
