package com.kethableez.walkerapi.Walk.Model.Utility;

import java.util.List;
import java.util.Set;

import com.kethableez.walkerapi.Utility.Enum.DogType;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkWithDog;

import lombok.Getter;

@Getter
public class WalkFilter {
    private List<String> intensities;
    private List<String> durations;
    private List<String> cities;
    private List<String> breeds;
    private Set<DogType> types;

    public WalkFilter(List<WalkWithDog> array) {
        this.intensities = this.getIntensityValues(array);
        this.durations = this.getDurationValues(array);
        this.cities = this.getCityValues(array);
        this.breeds = this.getBreedValues(array);
        this.types = this.getTypeValues(array);
    }

    private List<String> getIntensityValues(List<WalkWithDog> array) {
        return List.of(array.stream().map(obj -> obj.getDog().getWalkIntensity()).toArray(String[]::new));
    }

    private List<String> getDurationValues(List<WalkWithDog> array) {
        return List.of(array.stream().map(obj -> obj.getDog().getWalkDuration()).toArray(String[]::new));
    }

    private List<String> getCityValues(List<WalkWithDog> array) {
        return List.of(array.stream().map(obj -> obj.getWalk().getCity()).toArray(String[]::new));
    }

    private List<String> getBreedValues(List<WalkWithDog> array) {
        return List.of(array.stream().map(obj -> obj.getDog().getDogBreed()).toArray(String[]::new));
    }

    private Set<DogType> getTypeValues(List<WalkWithDog> array) {
        return Set.of(array.stream().map(obj -> obj.getDog().getDogType()).toArray(DogType[]::new));
    }

}
