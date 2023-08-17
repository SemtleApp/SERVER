package com.semtleapp.semtleapp.semtledues.repository;

import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemtleDuesRepository  extends JpaRepository<SemtleUserInfo, Long> {

    Optional<SemtleUserInfo>findByStudentId(String studentId);
}
