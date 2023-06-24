package com.semtleapp.semtleapp.semtlebook.service;

import com.semtleapp.semtleapp.semtlebook.dto.SemtleBookDto;
import com.semtleapp.semtleapp.semtlebook.repository.SemtleBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SemtleBookServiceImpl implements SemtleBookService {
    private final SemtleBookRepository semtleBookRepository;

    @Override
    public SemtleBookDto create(SemtleBookDto semtleBookDto) {

        return null;
    }

    @Override
    public List<SemtleBookDto> getBookList() {
        return null;
    }
}
