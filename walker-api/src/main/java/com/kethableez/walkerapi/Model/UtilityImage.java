package com.kethableez.walkerapi.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "utility_images")
@Getter
@Setter
@RequiredArgsConstructor
public class UtilityImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String filename;

    private String filetype;

    private ImageType imagetype;

    @Lob
    private byte[] filedata;

    public UtilityImage(String filename, String filetype, ImageType imagetype, byte[] filedata) {
        this.filename = filename;
        this.filetype = filetype;
        this.imagetype = imagetype;
        this.filedata = filedata;
    }
}
