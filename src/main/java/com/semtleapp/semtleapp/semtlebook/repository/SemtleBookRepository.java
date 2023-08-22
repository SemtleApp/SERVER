package com.semtleapp.semtleapp.semtlebook.repository;

import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemtleBookRepository extends JpaRepository<SemtleBook, Long> {

    Optional<SemtleBook> findBybookName(String bookName);
    SemtleBook findByBookId(Long BookId);
    boolean existsBybookName(String bookName);

}
