package com.semtleapp.semtleapp.semtlebook.repository;

import com.semtleapp.semtleapp.semtlebook.entity.SemtleBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemtleBookRepository extends JpaRepository<SemtleBook, Long> {

    SemtleBook findByName(String bookName);

}
