package com.semtleapp.semtleapp.semtlepost.service;

import com.semtleapp.semtleapp.semtlepost.dto.SemtlePostDto;

public interface SemtlePostService {

    SemtlePostDto create(String email, SemtlePostDto semtlePostDto);
}
