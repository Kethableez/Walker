package com.kethableez.walkerapi.Utility.Address.Controller;

import java.io.IOException;

import com.kethableez.walkerapi.Utility.Address.Model.District;
import com.kethableez.walkerapi.Utility.Address.Model.Response;
import com.kethableez.walkerapi.Utility.Address.Service.AddressService;
import com.kethableez.walkerapi.Utility.Response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/address")
public class AddressController {
    
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/getAddress/{postCode}")
    public ResponseEntity<?> getAddress(@PathVariable String postCode) {
        District response = addressService.findCity(postCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/uploadConfig")
    public ResponseEntity<?> uploadConfigFile(@RequestParam("configFile") MultipartFile configFile) {
        try {
            addressService.saveDistricts(configFile);
            return new ResponseEntity<>(new MessageResponse("Dodano pliki konfiguracyjne"), HttpStatus.OK);
        } 
        catch(IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
