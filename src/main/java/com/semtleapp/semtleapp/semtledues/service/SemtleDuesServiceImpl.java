package com.semtleapp.semtleapp.semtledues.service;

import com.semtleapp.semtleapp.semtledues.convertor.SemtleDuesConvertor;
import com.semtleapp.semtleapp.semtledues.dto.GetIngResDto;
import com.semtleapp.semtleapp.semtledues.entity.SemtleDues;
import com.semtleapp.semtleapp.semtledues.repository.SemtleDuesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SemtleDuesServiceImpl implements SemtleDuesService {

    private final SemtleDuesRepository semtleDuesRepository;

    @Override
    public List<GetIngResDto.IngList> getIng(String status) {
        List<SemtleDues> semtleDues = semtleDuesRepository.findByStatus(status);
        List<GetIngResDto.IngList> ingList = new ArrayList<>();
        semtleDues.forEach(
                result -> ingList.add(
                        SemtleDuesConvertor.creatIng(result)
                        )
                );

        return ingList;
    }
}
