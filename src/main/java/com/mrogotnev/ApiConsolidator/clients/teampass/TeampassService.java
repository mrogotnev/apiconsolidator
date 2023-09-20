package com.mrogotnev.ApiConsolidator.clients.teampass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrogotnev.ApiConsolidator.clients.teampass.mappers.TeampassMapper;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@AllArgsConstructor
@Data
@Component
public class TeampassService {
    private TeampassCredentials teampassCredentials;
    private final WebClient webClientWithoutSSL;
    private LinkedList<String> teampassFolders;
    private TeampassMapper teampassMapper;

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
            TypeReference<List<TeampassFolderApi>> typeReference = new TypeReference<>() {};
            allData.addAll(objectMapper.readValue(json, typeReference));
        }
        return allData;
    }

    public HashSet<PojoVM> getFoldersName() throws JsonProcessingException {
        List<TeampassFolderApi> teampassFoldersApi = getApiData();
        HashMap<Long, String> clusters = new HashMap<>();
        ArrayList<String> listOfClustersName = teampassCredentials.getFoldersName();
        HashSet<PojoVM> resultSet = new HashSet<>();
        for (TeampassFolderApi currentFolder : teampassFoldersApi) {
            for (String currentNameFromCred : listOfClustersName) {
                if (currentFolder.getTitle().equals(currentNameFromCred)) {
                    clusters.put(currentFolder.getId(), currentFolder.getTitle());
                }
            }
        }

        for (TeampassFolderApi currentFolder : teampassFoldersApi) {
            if (!clusters.containsKey(currentFolder.getId())) {
                if (!clusters.containsKey(currentFolder.getParent_id())) {
                    currentFolder = getParentIDFromClusters(currentFolder, clusters, teampassFoldersApi);
                }
                resultSet.add(teampassMapper.teampassFolderToPojoVM(currentFolder, clusters.get(currentFolder.getParent_id())));
            }
        }


        return resultSet;
    }

    public TeampassFolderApi getParentIDFromClusters(TeampassFolderApi currentFolder, HashMap<Long, String> clusters, List<TeampassFolderApi> teampassFoldersApi) {
        if (!clusters.containsKey(currentFolder.getParent_id())) {
            currentFolder.setParent_id(teampassFoldersApi.stream()
                    .filter(folderFromList -> folderFromList.getId() == currentFolder.getParent_id())
                    .findAny()
                    .get().getParent_id());
            getParentIDFromClusters(currentFolder, clusters, teampassFoldersApi);
        }
        return currentFolder;
    }
}
