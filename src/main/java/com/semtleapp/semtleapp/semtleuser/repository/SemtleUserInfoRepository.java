package com.semtleapp.semtleapp.semtleuser.repository;

import com.semtleapp.semtleapp.semtlestudy.repository.SemtleStudyPostRepository;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUser;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SemtleUserInfoRepository extends JpaRepository<SemtleUserInfo, Long> {

    Optional<SemtleUserInfo> findBySemtleUser(SemtleUser semtleUser);
    SemtleUserInfo findByInfoId(Long Id);

    @Query(value = "select p.file_name as 'fileName', p.file_download_path as 'fileDownLoadPath' from photo p where p.target_id=:userId and p.type='USER'", nativeQuery = true)
    GetProfileImage getProfileImage(@Param("userId") Long userId);

    interface GetProfileImage {
        String getFileName();
        String getFileDownLoadPath();
    }
}
