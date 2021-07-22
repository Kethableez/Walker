package com.kethableez.walkerapi.Repository;

import com.kethableez.walkerapi.Model.Enum.ImageType;
import com.kethableez.walkerapi.Model.Entity.UtilityImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilityImageRepository extends JpaRepository<UtilityImage, Long> {
    UtilityImage findByFilename(String filename);
    UtilityImage findByImagetype(ImageType imageType);
}
