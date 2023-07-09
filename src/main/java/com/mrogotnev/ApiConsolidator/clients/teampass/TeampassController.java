package com.mrogotnev.ApiConsolidator.clients.teampass;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@AllArgsConstructor
public class TeampassController {
    private TeampassService teampassService;

    @GetMapping("/getTeampassData")
    public HashSet<String> getApiData() throws JsonProcessingException {
        return teampassService.getFoldersName();
    }
}
