package com.mrogotnev.ApiConsolidator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxService;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxService;
import com.mrogotnev.ApiConsolidator.clients.selectel.SelectelService;
import com.mrogotnev.ApiConsolidator.clients.teampass.TeampassService;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@AllArgsConstructor
@Data
@Component
public class MainService {
    private ProxmoxService proxmoxService;
    private NetboxService netboxService;
    private SelectelService selectelService;
    private TeampassService teampassService;
    private HashSet<PojoVM> selectelPojoVM = new HashSet<>();
    private HashSet<PojoVM> proxmoxPojoVM = new HashSet<>();
    private HashSet<PojoVM> netboxPojoVM = new HashSet<>();
    private HashSet<String> teampassFolders = new HashSet<>();
    private HashSet<PojoVM> selectelAndProxmoxVM = new HashSet<>();
    //private boolean isInitialize = false;

    private boolean init() throws JsonProcessingException {
        if (proxmoxPojoVM.isEmpty() && netboxPojoVM.isEmpty() && selectelPojoVM.isEmpty() && teampassFolders.isEmpty()) {
            proxmoxPojoVM = proxmoxService.getVMList();
            netboxPojoVM = netboxService.getPojoNetboxVM();
            selectelPojoVM = selectelService.getSelectelPojoVM();
            teampassFolders = teampassService.getFoldersName();
            selectelAndProxmoxVM.addAll(proxmoxPojoVM);
            selectelAndProxmoxVM.addAll(selectelPojoVM);
            return true;
        }
        return false;
    }

    public HashSet<PojoVM> getNoInNetboxList() throws JsonProcessingException {
        init();
        HashSet<PojoVM> noInNetboxList = new HashSet<>();
        for (PojoVM currentProxVM : selectelAndProxmoxVM) {
            boolean noInNetbox = true;
            for (PojoVM currentNetboxVM : netboxPojoVM) {
                if (currentProxVM.getName().equals(currentNetboxVM.getName())) {
                    noInNetbox = false;
                }
            }
            if (noInNetbox) {
                noInNetboxList.add(currentProxVM);
            }
        }
        return noInNetboxList;
    }

    public HashSet<PojoVM> getNoInAllSystem() throws JsonProcessingException {
        init();
        HashSet<PojoVM> noInAllSystem = new HashSet<>();
        for (PojoVM currentVMFromNetbox : netboxPojoVM) {
            boolean noInSystem = true;
            for (PojoVM currentVMFromSystem : selectelAndProxmoxVM) {
                if (currentVMFromNetbox.getName().equals(currentVMFromSystem.getName())) {
                    noInSystem = false;
                }
            }
            if (noInSystem) {
                noInAllSystem.add(currentVMFromNetbox);
            }
        }
        return noInAllSystem;
    }

    public HashSet<String> getNoInTeampass() throws JsonProcessingException {
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
    }
}
