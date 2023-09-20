package com.mrogotnev.ApiConsolidator.dto;

import lombok.Data;

import java.util.Set;

@Data
public class NoInNetboxSet {
    private String name;
    private Set<PojoVM> pojoVMSet;

    public NoInNetboxSet() {
    }

    public NoInNetboxSet(String name, Set<PojoVM> pojoVMSet) {
        this.name = name;
        this.pojoVMSet = pojoVMSet;
    }
}
