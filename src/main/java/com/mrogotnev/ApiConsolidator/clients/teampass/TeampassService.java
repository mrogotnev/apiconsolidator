package com.mrogotnev.ApiConsolidator.clients.teampass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@Data
@Component
public class TeampassService {
    private TeampassCredentials teampassCredentials;
    private final WebClient webClientWithoutSSL;
    private HashSet<String> teampassFolders = new HashSet<>();

    public List<TeampassFolderApi> getApiData() throws JsonProcessingException {
        List<TeampassFolderApi> allData = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (String currentFolderFromConfig : teampassCredentials.getFoldersName()) {
            String json = webClientWithoutSSL
                    .get()
                    .uri(teampassCredentials.getUrl() + "/api/index.php/read/folder_descendants/title/"+
                            currentFolderFromConfig + "?apikey=" +
                            teampassCredentials.getApiToken())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            TypeReference<List<TeampassFolderApi>> typeReference = new TypeReference<List<TeampassFolderApi>>() {};
            allData.addAll(objectMapper.readValue(json, typeReference));
        }
        return allData;
    }

    public HashSet<String> getFoldersName() throws JsonProcessingException {
        for (TeampassFolderApi currentFolder : getApiData()) {
            teampassFolders.add(currentFolder.getTitle());
        }
        for (String nameFromCredentials : teampassCredentials.getFoldersName()) {
            teampassFolders.remove(nameFromCredentials);
        }
        return teampassFolders;
    }


}
