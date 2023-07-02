package com.mrogotnev.ApiConsolidator.services;

import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxService;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxService;
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
    private HashSet<PojoVM> proxmoxPojoVM = new HashSet<>();
    private HashSet<PojoVM> netboxPojoVM = new HashSet<>();
    //private boolean isInitialize = false;

    private boolean init() {
        if (proxmoxPojoVM.isEmpty() && netboxPojoVM.isEmpty()) {
            proxmoxPojoVM = proxmoxService.getVMList();
            netboxPojoVM = netboxService.getPojoNetboxVM();
            return true;
        }
        return false;
    }

    public HashSet<PojoVM> getNoInNetboxList() {
        init();
        HashSet<PojoVM> noInNetboxList = new HashSet<>();
        for (PojoVM currentProxVM : proxmoxPojoVM) {
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

    public HashSet<PojoVM> getNoInAllSystem() {
        init();
        HashSet<PojoVM> noInAllSystem = new HashSet<>();
        //TODO: слепить все сеты с вм в один сет и добавить проверку статуса из netbox
        for (PojoVM currentVMFromNetbox : netboxPojoVM) {
            boolean noInSystem = true;
            for (PojoVM currentVMFromSystem : proxmoxPojoVM) {
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
}
