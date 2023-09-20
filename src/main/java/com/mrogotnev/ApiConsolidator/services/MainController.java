package com.mrogotnev.ApiConsolidator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrogotnev.ApiConsolidator.dto.NoInNetboxSet;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

@RestController
@AllArgsConstructor
public class MainController {
    private MainService mainService;

    @GetMapping("/getNoInNetboxSet")
    public NoInNetboxSet getNoInNetboxSet() throws JsonProcessingException {
        return new NoInNetboxSet("NoInNetbox", mainService.getNoInNetboxSet());
    }

    @GetMapping("/getSetWithWrongClusterFromNetbox")
    public NoInNetboxSet getSetWithWrongClusterFromNetbox() throws JsonProcessingException {
        return new NoInNetboxSet("WrongCluster", mainService.getSetWithWrongClusterFromNetbox());
    }

    /*@GetMapping("/getNoInTeampassSet")
    public Set<PojoVM> getNoInTeampassSet() throws JsonProcessingException {
        return mainService.getNoInTeampassSet();
    }*/

    @GetMapping("/audit")
    public ArrayList<NoInNetboxSet> audit() throws JsonProcessingException {
        ArrayList<NoInNetboxSet> auditList = new ArrayList<>();
        auditList.add(new NoInNetboxSet("NoInNetbox", mainService.getNoInNetboxSet()));
        auditList.add(new NoInNetboxSet("WrongClusterFromNetbox", mainService.getSetWithWrongClusterFromNetbox()));
        auditList.add(new NoInNetboxSet("NoInTeampass", mainService.getNoInTeampassSet()));
        auditList.add(new NoInNetboxSet("WrongClusterFromTeampass", mainService.getSetWithWrongClusterFromTeampass()));
        return auditList;
    }

    /*@GetMapping("/test03")
    public HashSet<String> test03() throws JsonProcessingException {
        return mainService.getNoInTeampass();
    }*/
}
