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

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@AllArgsConstructor
@Data
@Component
public class ClientsFacade {
    private ProxmoxService proxmoxService;
    private NetboxService netboxService;
    private SelectelService selectelService;
    private TeampassService teampassService;

    public HashSet<PojoVM> getSelectelAndProxmoxVMSet() {
        HashSet<PojoVM> resultSet = new HashSet<>();
        resultSet.addAll(proxmoxService.getVMList());
        resultSet.addAll(selectelService.getSelectelPojoVM());
        return resultSet;
    }

    public HashSet<PojoVM> getNetboxVMSet() {
        return new HashSet<>(netboxService.getPojoNetboxVM());
    }

    public HashSet<PojoVM> getTeampassSet() throws JsonProcessingException {
        return teampassService.getFoldersName();
    }
}
