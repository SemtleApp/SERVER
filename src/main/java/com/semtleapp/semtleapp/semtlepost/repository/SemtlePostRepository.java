package com.semtleapp.semtleapp.semtlepost.repository;

import com.semtleapp.semtleapp.semtlepost.entity.SemtlePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SemtlePostRepository extends JpaRepository<SemtlePost, Long> {
    List<SemtlePost> findBySemtleUser_EmailOrderByCreatedDateDesc(String semtleUser_email);
}
