package com.mrogotnev.ApiConsolidator.clients.teampass.mappers;

import com.mrogotnev.ApiConsolidator.clients.teampass.TeampassFolderApi;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TeampassMapper {
    public String getStringFolderName(TeampassFolderApi teampassFolderApi) {
        return teampassFolderApi.getTitle();
    }
}
