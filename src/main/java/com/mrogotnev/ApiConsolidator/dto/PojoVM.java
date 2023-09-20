package com.mrogotnev.ApiConsolidator.dto;

        import lombok.Data;

@Data
public class PojoVM {
    public PojoVM() {

    }
    public PojoVM(String name, String cluster) {
        this.name = name;
        this.cluster = cluster;
    }
    private long idFromOutSystems;
    private String name;
    private String cluster;
    private double vcpu;
    private long memory;
    private int disk;
    private String status;
}
