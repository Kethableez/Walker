package com.kethableez.walkerapi.Image.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection="user_images")
@Getter
@Setter
public class UserImage {
    @Id
    private String id;

    private String userId;

    private String fileName;

    private byte[] file;


    public UserImage(String userId, String fileName, byte[] file) {
        this.userId = userId;
        this.fileName = fileName;
        this.file = file;
    }
    
}
