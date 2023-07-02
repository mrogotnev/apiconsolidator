package com.mrogotnev.ApiConsolidator.clients.netbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@RestController
@AllArgsConstructor
public class NetboxController {
    private NetboxService netboxService;

    @GetMapping("/getClusters")
    public HashMap<String, Long> getClusters() {
        return netboxService.getPojoNetboxClusters();
    }

    @GetMapping("/getVMs")
    public HashSet<PojoVM> getVMs() {
        return netboxService.getPojoNetboxVM();
    }

}
