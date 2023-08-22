package com.semtleapp.semtleapp.semtleuser.repository;

import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemtleUserInfoRepository extends JpaRepository<SemtleUserInfo, Long> {

    Optional<SemtleUserInfo> findBySemtleUser(SemtleUser semtleUser);
    SemtleUserInfo findByInfoId(Long Id);
}
