package com.semtleapp.semtleapp.semtlebook.repository;

import com.semtleapp.semtleapp.semtlebook.entity.SemtleBorrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemtleBorrowRepository extends JpaRepository<SemtleBorrow, Long> {
}
