package com.mrogotnev.ApiConsolidator.clients.teampass.mappers;

import com.mrogotnev.ApiConsolidator.clients.teampass.TeampassFolderApi;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TeampassMapper {
    public String getStringFolderName(TeampassFolderApi teampassFolderApi) {
        return teampassFolderApi.getTitle();
    }

    public PojoVM teampassFolderToPojoVM(TeampassFolderApi teampassFolderApi, String clusterName) {
        PojoVM pojoVM = new PojoVM();
        pojoVM.setName(teampassFolderApi.getTitle());
        if (clusterName.equals("Selectel_150034_Internal")) {
            pojoVM.setCluster("selectel");
        } else {
            pojoVM.setCluster(clusterName);
        }
        return pojoVM;
    }
}
