package com.mrogotnev.ApiConsolidator.clients.netbox;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
public class NetboxController {
    private NetboxService netboxService;
    @GetMapping("/netboxTestData")
    public NetboxApiVM netboxTestData() {
        return netboxService.netboxTestData();
    }

    @GetMapping("/getNetboxVMS")
    public ArrayList<NetboxVM> getNetboxVMS() {
        return netboxService.getNetboxVMS();
    }
}
