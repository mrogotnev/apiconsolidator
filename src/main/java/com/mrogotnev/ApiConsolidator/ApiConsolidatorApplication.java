package com.mrogotnev.ApiConsolidator;

import com.mrogotnev.ApiConsolidator.clients.proxmox.ProxmoxService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiConsolidatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiConsolidatorApplication.class, args);
	}


}
