package com.kethableez.walkerapi.Sitter.Service;

import com.kethableez.walkerapi.Sitter.Model.Sitter;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SitterService {
    @Autowired
    private final MapperService mapper;

    public Sitter getSitterData(String sitterId) {
        return mapper.sitterMapper(sitterId);
    }

}
