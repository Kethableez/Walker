package com.kethableez.walkerapi.Walk.Model.DTO;

import java.util.List;

import com.kethableez.walkerapi.Walk.Model.Utility.WalkFilter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WalkWithFilters {
    private List<WalkInfo> walks;
    private WalkFilter filters;
}
