package com.semtleapp.semtleapp.file.repository;

import com.semtleapp.semtleapp.file.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByTargetIdAndType(Long targetId, String type);
    void deleteByPhotoId(Long photoId);

    Optional<Photo> findByUuId(String uuId);
}
