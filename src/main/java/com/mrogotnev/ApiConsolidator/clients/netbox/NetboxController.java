package com.mrogotnev.ApiConsolidator.clients.netbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@AllArgsConstructor
public class NetboxController {
    private NetboxService netboxService;

    @GetMapping("/getClusters")
    public HashMap<String, Long> getClusters() {
        return netboxService.getPojoNetboxClusters();
    }

    @GetMapping("/getVMs")
    public HashMap<String, PojoVM> getVMs() {
        return netboxService.getPojoNetboxVM();
    }

}
