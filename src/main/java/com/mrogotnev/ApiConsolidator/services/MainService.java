package com.mrogotnev.ApiConsolidator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Component
public class MainService {
    private ClientsFacade clientsFacade;

    public Set<PojoVM> getNoInNetboxSet() throws JsonProcessingException {
        HashSet<PojoVM> selectelAndProxmoxVMSet = clientsFacade.getSelectelAndProxmoxVMSet();
        HashSet<PojoVM> netboxVMSet = clientsFacade.getNetboxVMSet();
        return selectelAndProxmoxVMSet.stream()
                .filter(sysVM -> netboxVMSet.stream()
                        .noneMatch(netboxVM -> sysVM.getName().equalsIgnoreCase(netboxVM.getName())))
                        .collect(Collectors.toSet());
    }

    public Set<PojoVM> getSetWithWrongClusterFromNetbox() throws JsonProcessingException {
        HashSet<PojoVM> selectelAndProxmoxVMSet = clientsFacade.getSelectelAndProxmoxVMSet();
        HashSet<PojoVM> netboxVMSet = clientsFacade.getNetboxVMSet();
        return selectelAndProxmoxVMSet.stream()
                .filter(sysVM -> netboxVMSet.stream()
                        .anyMatch(netboxVM ->
                                sysVM.getName().equalsIgnoreCase(netboxVM.getName()) &&
                                !sysVM.getCluster().equalsIgnoreCase(netboxVM.getCluster())))
                .collect(Collectors.toSet());
    }

    public Set<PojoVM> getNoInTeampassSet() throws JsonProcessingException {
        HashSet<PojoVM> selectelAndProxmoxVMSet = clientsFacade.getSelectelAndProxmoxVMSet();
        HashSet<PojoVM> teampassFolders = clientsFacade.getTeampassSet();

        return selectelAndProxmoxVMSet.stream()
                .filter(sysVM -> teampassFolders.stream()
                        .noneMatch(teamPassFolder ->
                                sysVM.getName().equalsIgnoreCase(teamPassFolder.getName())))
                .collect(Collectors.toSet());
    }

    public Set<PojoVM> getSetWithWrongClusterFromTeampass() throws JsonProcessingException {
        HashSet<PojoVM> selectelAndProxmoxVMSet = clientsFacade.getSelectelAndProxmoxVMSet();
        HashSet<PojoVM> teampassFolders = clientsFacade.getTeampassSet();
        return selectelAndProxmoxVMSet.stream()
                .filter(sysVM -> teampassFolders.stream()
                        .anyMatch(netboxVM ->
                                sysVM.getName().equalsIgnoreCase(netboxVM.getName()) &&
                                !sysVM.getCluster().equalsIgnoreCase(netboxVM.getCluster())))
                .collect(Collectors.toSet());
    }

    public Set<PojoVM> getNoInAllSystem() throws JsonProcessingException {
        HashSet<PojoVM> selectelAndProxmoxVMSet = clientsFacade.getSelectelAndProxmoxVMSet();
        HashSet<PojoVM> netboxVMSet = clientsFacade.getNetboxVMSet();
        return netboxVMSet.stream()
                .filter(sysVM -> selectelAndProxmoxVMSet.stream()
                        .noneMatch(netboxVM ->
                                sysVM.getName().equals(netboxVM.getName()) &&
                                sysVM.getCluster().equals(netboxVM.getCluster())))
                .collect(Collectors.toSet());
    }

    /*public HashSet<String> getNoInTeampass() throws JsonProcessingException {
        init();
        HashSet<String> noInAllTeampass = new HashSet<>();
        for (PojoVM currentVMFromSystem : selectelAndProxmoxVM) {
            boolean noInTeampass = true;
            for (String currentFolder : teampassFolders) {
                if (currentVMFromSystem.getName().equals(currentFolder)) {
                    noInTeampass = false;
                }
            }
            if (noInTeampass) {
                noInAllTeampass.add(currentVMFromSystem.getName());
            }
        }

        return noInAllTeampass;
    }*/
}
