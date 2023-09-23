package com.mrogotnev.ApiConsolidator.services;

import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VmAnalyzerService {

    public Set<PojoVM> getNoInDocumentationSet(Set<PojoVM> fromSystem, Set<PojoVM> fromDocumentation) {
        return fromSystem.stream()
                .filter(sysVM -> fromDocumentation.stream()
                        .noneMatch(netboxVM -> sysVM.getName().equalsIgnoreCase(netboxVM.getName())))
                .collect(Collectors.toSet());
    }

    public Set<PojoVM> getSetWithWrongClusterFromDocumentation(Set<PojoVM> fromSystem, Set<PojoVM> fromDocumentation) {
        return fromSystem.stream()
                .filter(sysVM -> fromDocumentation.stream()
                        .anyMatch(netboxVM ->
                                sysVM.getName().equalsIgnoreCase(netboxVM.getName()) &&
                                        !sysVM.getCluster().equalsIgnoreCase(netboxVM.getCluster())))
                .collect(Collectors.toSet());
    }
}
