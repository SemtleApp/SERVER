package com.semtleapp.semtleapp.common.repository;

import com.semtleapp.semtleapp.common.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByTargetIdAndType(Long targetId, String type);
    void deleteByPhotoId(Long photoId);

    Optional<Photo> findByUuId(String uuId);
}
