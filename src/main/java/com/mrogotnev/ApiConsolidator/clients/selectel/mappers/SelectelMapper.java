package com.mrogotnev.ApiConsolidator.clients.selectel.mappers;

import com.mrogotnev.ApiConsolidator.clients.selectel.SelectelApiVMData;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SelectelMapper {
    public PojoVM selectelApiVMToPojo(SelectelApiVMData.SelectelApiVm selectelApiVm) {
        PojoVM pojoVM = new PojoVM();
        pojoVM.setName(selectelApiVm.getName());
        pojoVM.setCluster("selectel");
        pojoVM.setStatus(selectelApiVm.getStatus());
        pojoVM.setMemory(selectelApiVm.getMemoryMB());
        pojoVM.setVcpu(selectelApiVm.getNumberOfCpus());
        pojoVM.setDisk((int) (selectelApiVm.getTotalStorageAllocatedMb()/1024));
        return pojoVM;
    }
}
