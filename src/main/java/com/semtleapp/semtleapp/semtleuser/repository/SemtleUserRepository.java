package com.semtleapp.semtleapp.semtleuser.repository;

import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemtleUserRepository extends JpaRepository<SemtleUser, Long> {
    Optional<SemtleUser> findByEmail(String email);
}
