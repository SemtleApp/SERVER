package com.semtleapp.semtleapp.semtlestudy.repository;

import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SemtleStudyPostRepository extends JpaRepository<SemtleStudyPost, Long> {

    @Query(value = "select s.title'title', u.email'email', s.created_date'createdDate'," +
            "              IF((select exists(select * from photo p where p.target_id = s.post_id)),'true', 'false') 'isFileCheck'\n" +
            "       from semtle_study s\n" +
            "       join semtle_user u on s.user_id = u.user_id\n" +
            "       join semtle_study_room sr on s.study_id = sr.study_id\n" +
            "       where sr.room_name=:roomName", nativeQuery = true)
    List<GetStudyPostList> getStudyPost(@Param("roomName") String roomName);

    interface GetStudyPostList {

        String getTitle();
        String getEmail();
        LocalDate getCreatedDate();
        boolean getIsFileCheck();

    }

}