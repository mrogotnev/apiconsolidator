package com.mrogotnev.ApiConsolidator.clients.selectel;

import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@AllArgsConstructor
public class SelectelController {
    private SelectelService selectelService;

    @GetMapping("/getSelectelToken")
    public String getSelectelToken() {
        return selectelService.getSelectelToken();
    }

    @GetMapping("/getStringData")
    public SelectelApiVMData getStringData() {
        SelectelApiVMData selectelApiVMData = selectelService.getVMList();
        return selectelApiVMData;
    }

    @GetMapping("/getSelectelVM")
    public HashSet<PojoVM> getSelectelVM() {
        return selectelService.getSelectelPojoVM();
    }
}
