package com.semtleapp.semtleapp.semtlestudy.repository;

import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemtleStudyRoomRepository extends JpaRepository<SemtleStudyRoom, Long> {
    Optional<SemtleStudyRoom> findById(Long studyId);
}
