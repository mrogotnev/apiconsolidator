package com.mrogotnev.ApiConsolidator.clients.netbox;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@Component
public class NetboxApiVM {
    private String count;
    private String next;
    private String previous;
    private ArrayList<NetboxVM> results;

    @Data
    public static class NetboxVM {
        private String name;
        private Cluster cluster;
        private double vcpus;
        private long memory;
        private int disk;
        private Status status;

        @Data
        private static class Cluster {
            private long id;
            private String url;
            private String name;
        }

        @Data
        private static class Status {
            private String value;
        }


    }

}
