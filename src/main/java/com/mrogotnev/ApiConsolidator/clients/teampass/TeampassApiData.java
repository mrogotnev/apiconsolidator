package com.mrogotnev.ApiConsolidator.clients.teampass;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
public class TeampassApiData {
    private ArrayList<TeampassFolder> array;

    @Data
    public static class TeampassFolder {
        private String title;
    }
}
