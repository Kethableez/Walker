package com.kethableez.walkerapi.Utility.Address.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "districts")
public class District {
    @Id
    private String id;
    private String districtCode;
    private String regionCode;
    private String districtName;
    private String districtCity;
    private String voivodeship;



    public District(String districtCode, String regionCode, String districtCity, String districtName, String voivodeship) {
        this.districtCode = districtCode;
        this.regionCode = regionCode;
        this.districtName = districtName;
        this.districtCity = districtCity;
        this.voivodeship = voivodeship;
    }


}
