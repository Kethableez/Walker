package com.kethableez.walkerapi.Owner.Service;

import com.kethableez.walkerapi.Owner.Model.Owner;
import com.kethableez.walkerapi.Utility.Mapper.MapperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OwnerService {
    @Autowired
    private final MapperService mapper;

    public Owner getData(String ownerId) {
        return mapper.ownerMapper(ownerId);
    }
}