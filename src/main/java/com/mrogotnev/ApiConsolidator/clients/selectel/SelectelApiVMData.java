package com.mrogotnev.ApiConsolidator.clients.selectel;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
public class SelectelApiVMData {
    private ArrayList<SelectelApiVm> record;

    @Data
    public static class SelectelApiVm {
        private String name;
        private int numberOfCpus;
        private long memoryMB;
        private long totalStorageAllocatedMb;
        private String status;
    }
}
