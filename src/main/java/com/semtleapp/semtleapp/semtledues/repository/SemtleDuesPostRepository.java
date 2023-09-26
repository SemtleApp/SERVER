package com.semtleapp.semtleapp.semtledues.repository;

import com.semtleapp.semtleapp.semtledues.entity.SemtleDuesReceiptPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemtleDuesPostRepository extends JpaRepository<SemtleDuesReceiptPost, Long> {
}
