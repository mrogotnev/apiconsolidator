package com.mrogotnev.ApiConsolidator.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AuditResponse {
    public Set<PojoVM> noInNetbox;
    public Set<PojoVM> wrongClusterFromNetbox;
    public Set<PojoVM> noInTeampass;
    public Set<PojoVM> wrongClusterFromTeampass;
}
