package com.kethableez.walkerapi.Image.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection="dog_images")
@Getter
@Setter
public class DogImage {
    
    @Id
    private String id;

    private String dogId;

    private String fileName;

    private byte[] file;


    public DogImage(String dogId, String fileName, byte[] file) {
        this.dogId = dogId;
        this.fileName = fileName;
        this.file = file;
    }

}
