package com.semtleapp.semtleapp.semtleuser.repository;

import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemtleUserRepository extends JpaRepository<SemtleUser, Long> {
    Optional<SemtleUser> findByEmail(String email);

    boolean existsByEmailAndSocial(String email, String social);

    boolean existsSemtleUserByEmailAndSocial(String email, String social);

    Optional<SemtleUser> findSemtleUserByEmailAndSocial(String email, String social);

}
