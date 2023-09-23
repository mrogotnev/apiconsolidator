package com.mrogotnev.ApiConsolidator.services;

import com.mrogotnev.ApiConsolidator.dto.AuditResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {
    private MainService mainService;

    @GetMapping("/audit")
    public AuditResponse audit() {
        return mainService.getAuditResult();
    }
}
