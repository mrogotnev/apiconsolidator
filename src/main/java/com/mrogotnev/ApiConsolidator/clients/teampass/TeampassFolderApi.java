package com.mrogotnev.ApiConsolidator.clients.teampass;

import lombok.Data;

@Data
public class TeampassFolderApi {
    private long id;
    private long parent_id;
    private String title;
    private long nleft;
    private long nright;
    private int nlevel;
    private int personal;

}
