package com.mrogotnev.ApiConsolidator.dto;

import lombok.Data;

@Data
public class PojoVM {
    private String name;
    private String cluster;
    private long clusterId;
    private double vcpu;
    private long memory;
    private int disk;
    private String status;
}
