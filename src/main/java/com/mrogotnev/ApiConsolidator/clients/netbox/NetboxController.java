package com.mrogotnev.ApiConsolidator.clients.netbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrogotnev.ApiConsolidator.clients.dto.PojoVM;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@AllArgsConstructor
public class NetboxController {
    private NetboxService netboxService;
    @GetMapping("/netboxTestData")
    public NetboxApiVM netboxTestData() {
        return netboxService.getNetboxAPIVm();
    }

    @GetMapping("/getNetboxVMS")
    public ArrayList<PojoVM> getNetboxVMS() throws JsonProcessingException {
        return netboxService.getNetboxVMS();
    }

    @GetMapping("/getApiClusters")
    public HashMap<String, Long> test() {
        return netboxService.getNetboxClusters();
    }
}
