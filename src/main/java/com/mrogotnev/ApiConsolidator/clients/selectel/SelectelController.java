package com.mrogotnev.ApiConsolidator.clients.selectel;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SelectelController {
    private SelectelService selectelService;

    @GetMapping("/getSelectelToken")
    public String getSelectelToken() {
        return selectelService.getSelectelToken();
    }
}
