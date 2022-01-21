package com.kethableez.walkerapi.Utility.Address.Repository;

import java.util.List;

import com.kethableez.walkerapi.Utility.Address.Model.District;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends MongoRepository<District, String>{
    List<District> findByDistrictName(String districtName);
    List<District> findByDistrictCity(String districtCity);
}
