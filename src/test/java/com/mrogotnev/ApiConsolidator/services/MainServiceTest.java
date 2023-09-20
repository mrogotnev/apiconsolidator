package com.mrogotnev.ApiConsolidator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrogotnev.ApiConsolidator.clients.teampass.TeampassService;
import com.mrogotnev.ApiConsolidator.dto.PojoVM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainServiceTest {
    @Mock
    private ClientsFacade clientsFacade;
    @Mock
    private TeampassService teampassService;
    @Mock
    private HashSet<PojoVM> selectelAndProxmoxVM = new HashSet<>();
    @InjectMocks
    private MainService mainService;

    @BeforeAll
    public void init() {

    }

    @Test
    void getNoInNetboxSetAndWrongCluster() throws JsonProcessingException {
        HashSet<PojoVM> proxmoxAndSelectelSet = new HashSet<>();
        PojoVM pojoVMProxmoxOrSelectel01 = new PojoVM("someVm01", "someCluster01");
        PojoVM pojoVMProxmoxOrSelectel02 = new PojoVM("someVm02", "someCluster01");
        PojoVM pojoVMProxmoxOrSelectel03 = new PojoVM("someVm01", "someCluster02");
        PojoVM pojoVMProxmoxOrSelectel04 = new PojoVM("someVm03", "someCluster02");
        proxmoxAndSelectelSet.add(pojoVMProxmoxOrSelectel01);
        proxmoxAndSelectelSet.add(pojoVMProxmoxOrSelectel02);
        proxmoxAndSelectelSet.add(pojoVMProxmoxOrSelectel03);
        proxmoxAndSelectelSet.add(pojoVMProxmoxOrSelectel04);

        HashSet<PojoVM> netboxSet = new HashSet<>();
        PojoVM pojoVMNetbox01 = new PojoVM("someVm01", "someCluster01");
        PojoVM pojoVMNetbox02 = new PojoVM("someVm02", "someCluster01");
        PojoVM pojoVMNetbox03 = new PojoVM("someVm01", "someCluster02");
        netboxSet.add(pojoVMNetbox01);
        netboxSet.add(pojoVMNetbox02);
        netboxSet.add(pojoVMNetbox03);

        HashSet<PojoVM> resultSet = new HashSet<>();
        resultSet.add(pojoVMProxmoxOrSelectel04);

        Mockito.when(clientsFacade.getSelectelAndProxmoxVMSet()).thenReturn(proxmoxAndSelectelSet);
        Mockito.when(clientsFacade.getNetboxVMSet()).thenReturn(netboxSet);

        Set<PojoVM> pojoVMHashSet = mainService.getNoInNetboxSet();
        Assertions.assertEquals(resultSet, pojoVMHashSet);
    }

    @Test
    void getSetWithWrongClusterFromNetbox() throws JsonProcessingException {
        HashSet<PojoVM> proxmoxAndSelectelSet = new HashSet<>();
        PojoVM pojoVMProxmoxOrSelectel01 = new PojoVM("jira", "dev11");
        PojoVM pojoVMProxmoxOrSelectel02 = new PojoVM("conf", "dev11");
        PojoVM pojoVMProxmoxOrSelectel03 = new PojoVM("jira", "srvProx");
        //PojoVM pojoVMProxmoxOrSelectel04 = new PojoVM("someVm02", "srvProx");
        proxmoxAndSelectelSet.add(pojoVMProxmoxOrSelectel01);
        proxmoxAndSelectelSet.add(pojoVMProxmoxOrSelectel02);
        proxmoxAndSelectelSet.add(pojoVMProxmoxOrSelectel03);

        HashSet<PojoVM> netboxSet = new HashSet<>();
        PojoVM pojoVMNetbox01 = new PojoVM("jira", "dev11");
        PojoVM pojoVMNetbox02 = new PojoVM("conf", "srvProx");
        netboxSet.add(pojoVMNetbox01);
        netboxSet.add(pojoVMNetbox02);

        HashSet<PojoVM> resultSet = new HashSet<>();
        resultSet.add(pojoVMProxmoxOrSelectel02);
        resultSet.add(pojoVMProxmoxOrSelectel03);

        Mockito.when(clientsFacade.getSelectelAndProxmoxVMSet()).thenReturn(proxmoxAndSelectelSet);
        Mockito.when(clientsFacade.getNetboxVMSet()).thenReturn(netboxSet);

        Set<PojoVM> pojoVMHashSet = mainService.getSetWithWrongClusterFromNetbox();
        Assertions.assertEquals(resultSet, pojoVMHashSet);
    }
}
