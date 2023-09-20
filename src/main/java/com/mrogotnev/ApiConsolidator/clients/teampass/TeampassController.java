package com.mrogotnev.ApiConsolidator.clients.teampass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@AllArgsConstructor
public class TeampassController {
    private TeampassService teampassService;

    @GetMapping("/getTeampassData")
    public HashSet<PojoVM> getApiData() throws JsonProcessingException {
        return teampassService.getFoldersName();
    }
}
