package com.semtleapp.semtleapp.common.repository;

import com.semtleapp.semtleapp.common.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
