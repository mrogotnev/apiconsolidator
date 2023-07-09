package com.mrogotnev.ApiConsolidator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    public HashSet<PojoVM> test01() throws JsonProcessingException {
        return mainService.getNoInNetboxList();
    }

    @GetMapping("/test02")
    public HashSet<PojoVM> test02() throws JsonProcessingException {
        return mainService.getNoInAllSystem();
    }

    @GetMapping("/test03")
    public HashSet<String> test03() throws JsonProcessingException {
        return mainService.getNoInTeampass();
    }
}
