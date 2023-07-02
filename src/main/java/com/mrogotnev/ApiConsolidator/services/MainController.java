package com.mrogotnev.ApiConsolidator.services;

import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private MainService mainService;

    @GetMapping("/test01")
    public HashSet<PojoVM> test01() {
        return mainService.getNoInNetboxList();
    }

    @GetMapping("test02")
    public HashSet<PojoVM> test02() {
        return mainService.getNoInAllSystem();
    }
}
