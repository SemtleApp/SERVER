package com.semtleapp.semtleapp.semtlestudy.repository;

import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SemtleStudyRoomRepository extends JpaRepository<SemtleStudyRoom, Long> {

    Optional<SemtleStudyRoom> findById(Long studyId);

    @Query(value = "select sr.room_name'roomName' from semtle_study_room sr join semtle_study_belong sb on sr.study_id = sb.study_id where sb.user_id=:userId",nativeQuery = true)
    List<GetBelongStudyList> getBelongStudy(@Param("userId") Long userId);

    interface GetBelongStudyList{

        String getRoomName();

    }
}
