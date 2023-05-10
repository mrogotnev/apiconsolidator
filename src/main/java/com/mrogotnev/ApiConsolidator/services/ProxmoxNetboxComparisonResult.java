package com.mrogotnev.ApiConsolidator.services;

import com.mrogotnev.ApiConsolidator.clients.netbox.NetboxService;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxService;
import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxVM;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
public class ProxmoxNetboxComparisonResult {
    private ArrayList<ProxmoxVM> proxmoxVMSWhichIsNotInNetbox;
    private ArrayList<ProxmoxVM> proxmoxVMSWithDifferentClusterName;
    private ArrayList<ProxmoxVM> proxmoxVMSWithDifferentHardwareValue;
}
