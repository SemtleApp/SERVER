package com.semtleapp.semtleapp.semtledues.service;

import com.semtleapp.semtleapp.semtledues.convertor.SemtleDuesConvertor;
import com.semtleapp.semtleapp.semtledues.dto.GetDuesResDto;
import com.semtleapp.semtleapp.semtledues.repository.SemtleDuesRepository;
import com.semtleapp.semtleapp.semtleuser.entity.SemtleUserInfo;
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
    public List<GetDuesResDto.DuesList> getDues() {
        List<SemtleUserInfo> semtleUserInfo = semtleDuesRepository.findAll();
        List<GetDuesResDto.DuesList> duesList = new ArrayList<>();
        semtleUserInfo.forEach(
                result -> duesList.add(
                        SemtleDuesConvertor.getDues(result)
                        )
                );

        return duesList;
    }
}
