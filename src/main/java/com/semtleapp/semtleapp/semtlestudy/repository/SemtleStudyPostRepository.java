package com.semtleapp.semtleapp.semtlestudy.repository;

import com.semtleapp.semtleapp.semtlestudy.dto.GetStudyPostDetailResDto;
import com.semtleapp.semtleapp.semtlestudy.entity.SemtleStudyPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface SemtleStudyPostRepository extends JpaRepository<SemtleStudyPost, Long> {

    @Query(value = "select s.title'title', u.email'email', s.created_date'createdDate'," +
            "              IF((select exists(select * from photo p where p.target_id = s.post_id)),'true', 'false') 'isFileCheck'\n" +
            "       from semtle_study s\n" +
            "       join semtle_user u on s.user_id = u.user_id\n" +
            "       join semtle_study_room sr on s.study_id = sr.study_id\n" +
            "       where sr.room_name=:roomName", nativeQuery = true)
    List<GetStudyPostList> getStudyPost(@Param("roomName") String roomName);

    @Query(value = "select s.title'title'," +
            "              s.content'content', " +
            "              ui.nickname'nickName'," +
            "              sr.room_name'roomName', " +
            "              s.created_date'createdDate' " +
            "              from semtle_study s" +
            "              join semtle_user_info ui on s.user_id = ui.user_id" +
            "              join semtle_study_room sr on s.study_id = sr.study_id" +
            "              where s.post_id=:postId", nativeQuery = true)
    GetStudyPost getStudyPostDetail(@Param("postId") Long postId);

    @Query(value = "select p.file_name'fileName', p.file_download_path'fileDownLoadPath' from photo p where p.target_id=:postId and p.type='STUDY'", nativeQuery = true)
    List<GetFileList> getfiles(@Param("postId") Long postId);

    interface GetStudyPostList {

        String getTitle();
        String getEmail();
        LocalDate getCreatedDate();
        boolean getIsFileCheck();

    }

    interface GetStudyPost {

        String getRoomName();
        String getTitle();
        String getContent();
        String getNickName();
        LocalDate getCreatedDate();

    }

    interface GetFileList {

        String getFileName();
        String getFileDownLoadPath();

    }

}