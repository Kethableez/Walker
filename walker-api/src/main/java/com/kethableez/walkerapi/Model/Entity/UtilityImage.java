package com.kethableez.walkerapi.Model.Entity;

import com.kethableez.walkerapi.Model.Enum.ImageType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UtilityImage {

    private Long Id;

    private String filename;

    private String filetype;

    private ImageType imagetype;

    private byte[] filedata;

    public UtilityImage(String filename, String filetype, ImageType imagetype, byte[] filedata) {
        this.filename = filename;
        this.filetype = filetype;
        this.imagetype = imagetype;
        this.filedata = filedata;
    }
}
