package com.kethableez.walkerapi.Utility.Address.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Response {

    @JsonProperty("kod")
    private String postCode;

    @JsonProperty("miejscowosc")
    private String city;

    @JsonProperty("powiat")
    private String district;

    @JsonProperty("wojewodztwo")
    private String voivodenship;
}
