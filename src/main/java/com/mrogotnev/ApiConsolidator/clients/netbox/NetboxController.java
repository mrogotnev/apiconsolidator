package com.mrogotnev.ApiConsolidator.clients.netbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
public class NetboxController {
    private NetboxService netboxService;
    @GetMapping("/netboxTestData")
    public NetboxApiVM netboxTestData() {
        return netboxService.getNetboxAPIVm();
    }

    @GetMapping("/getNetboxVMS")
    public ArrayList<NetboxVM> getNetboxVMS() throws JsonProcessingException {
        return netboxService.getNetboxVMS();
    }
}
