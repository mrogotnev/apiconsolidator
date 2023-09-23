package com.mrogotnev.ApiConsolidator.services;

import com.mrogotnev.ApiConsolidator.dto.AuditResponse;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Data
@Component
@AllArgsConstructor
public class MainService {
    private ClientsFacade clientsFacade;
    private VmAnalyzerService vmAnalyzerService;

    public AuditResponse getAuditResult() {
        HashSet<PojoVM> selectelAndProxmoxVMSet = clientsFacade.getSelectelAndProxmoxVMSet();
        HashSet<PojoVM> netboxVMSet = clientsFacade.getNetboxVMSet();
        HashSet<PojoVM> teampassFolders = clientsFacade.getTeampassSet();
        AuditResponse auditResponse = new AuditResponse();
        if (!netboxVMSet.isEmpty()) {
            auditResponse.setNoInNetbox(vmAnalyzerService.getNoInDocumentationSet(selectelAndProxmoxVMSet, netboxVMSet));
            auditResponse.setWrongClusterFromNetbox(vmAnalyzerService.getSetWithWrongClusterFromDocumentation(selectelAndProxmoxVMSet, netboxVMSet));
        }
        if (!teampassFolders.isEmpty()) {
            auditResponse.setNoInTeampass(vmAnalyzerService.getNoInDocumentationSet(selectelAndProxmoxVMSet, teampassFolders));
            auditResponse.setWrongClusterFromTeampass(vmAnalyzerService.getSetWithWrongClusterFromDocumentation(selectelAndProxmoxVMSet, teampassFolders));
        }
        return auditResponse;
    }
}
