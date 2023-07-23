package com.semtleapp.semtleapp.semtleuser.repository;

import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemtleUserInfoRepository extends JpaRepository<SemtleUserInfo, Long> {

    Optional<SemtleUserInfo> findByUserId(Long userId);
}
