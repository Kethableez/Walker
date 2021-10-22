package com.kethableez.walkerapi.Walk.Model.Utility;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.kethableez.walkerapi.Utility.Enum.DogType;
import com.kethableez.walkerapi.Walk.Model.DTO.WalkCard;

import lombok.Getter;

@Getter
public class WalkFilter {
    private Set<String> intensities;
    private Set<String> durations;
    private Set<String> cities;
    private Set<String> breeds;
    private Set<DogType> types;

    public WalkFilter(List<WalkCard> array) {
        this.intensities = this.getIntensityValues(array);
        this.durations = this.getDurationValues(array);
        this.cities = this.getCityValues(array);
        this.breeds = this.getBreedValues(array);
        this.types = this.getTypeValues(array);
    }

    private Set<String> getIntensityValues(List<WalkCard> array) {
        // return Set.of(array.stream().map(obj -> obj.getDog().getWalkIntensity()).toArray(String[]::new));
        return array.stream().map(walkCard -> walkCard.getDog().getWalkIntensity()).collect(Collectors.toSet());
    }

    private Set<String> getDurationValues(List<WalkCard> array) {
        return array.stream().map(walkCard -> walkCard.getDog().getWalkDuration()).collect(Collectors.toSet());
    }

    private Set<String> getCityValues(List<WalkCard> array) {
        return array.stream().map(walkCard -> walkCard.getWalk().getCity()).collect(Collectors.toSet());
    }

    private Set<String> getBreedValues(List<WalkCard> array) {
        return array.stream().map(walkCard -> walkCard.getDog().getDogBreed()).collect(Collectors.toSet());
    }

    private Set<DogType> getTypeValues(List<WalkCard> array) {
        return array.stream().map(walkCard -> walkCard.getDog().getDogType()).collect(Collectors.toSet());
    }

}
