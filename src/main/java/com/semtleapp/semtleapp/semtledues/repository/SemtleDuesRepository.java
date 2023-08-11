package com.semtleapp.semtleapp.semtledues.repository;

import com.semtleapp.semtleapp.semtledues.entity.SemtleDues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemtleDuesRepository  extends JpaRepository<SemtleDues, Long> {
    List<SemtleDues> findByStatus(String status);
}
