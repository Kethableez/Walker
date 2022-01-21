package com.kethableez.walkerapi.Utility.Address.Service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.kethableez.walkerapi.Utility.Address.Model.District;
import com.kethableez.walkerapi.Utility.Address.Model.Response;
import com.kethableez.walkerapi.Utility.Address.Repository.DistrictRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AddressService {

    private static final String apiUrl = "http://kodpocztowy.intami.pl/api/%s";
    private final DistrictRepository districtRepository;

    @Autowired
    public AddressService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public District findCity(String postCode) {
        Response response = this.getDistrictCode(postCode);
        if(!districtRepository.findByDistrictName(response.getDistrict()).isEmpty()) {
            return districtRepository.findByDistrictName(response.getDistrict()).get(0);
        }
        else return districtRepository.findByDistrictCity(response.getCity()).get(0);
    }

    public Response getDistrictCode(String postCode) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);



        URI completeUrl = UriComponentsBuilder
        .fromHttpUrl(String.format(apiUrl, postCode))
        .build()
        .encode()
        .toUri();

        ResponseEntity<Response[]> response = template.exchange(
            completeUrl, 
            HttpMethod.GET, 
            entity, 
            Response[].class);
        
        return response.getBody()[0];
    }

    public void saveDistricts(MultipartFile config) throws IOException {
        Scanner scanner = new Scanner(config.getInputStream());

        List<District> districts = scanner.useDelimiter("\n").tokens()
        .map(line -> line.split("\t"))
        .map(array -> this.districtMapper(array))
        .collect(Collectors.toList());

        scanner.close();
        districtRepository.saveAll(districts);
    }

    private District districtMapper(String[] splittedString) {
        return new District(
            splittedString[0], 
            splittedString[1], 
            splittedString[2], 
            splittedString[3], 
            splittedString[4]);
    } 
}
