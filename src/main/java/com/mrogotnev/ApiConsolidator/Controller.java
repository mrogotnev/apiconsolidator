package com.mrogotnev.ApiConsolidator;


import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;


@RestController
@AllArgsConstructor
public class Controller {
    private final WebClient webClient;
    private final String cookieValue = "PVE:root@pam:63FB3287::dsBTN5/6bgxMmNWSR5qV+F4bLZm7TbZ62hTbQCp+XHQ3Rlnu2um6FZxB4v9PRaXD0p3PHWMnvLqkZj3Z/2FilsFgyfvh8QHEW5FrPOOseD+EtqbjuYOUp2F7m29aGn68TWU+IFsx7YKi5yqJptOqSvAu2WPleymQ5ZnISdvrsP1egfctr1RjaUUAZCXq3lN4bX6Nig2jlFgSmAIiCI80ICPV5tU3WDrYKAMf3ffZ2IWSiTkzAKoBYdGVFvn+L63Qcd41ANHIYEJ4j3ZeamyJck/OS0ybHfGnjl0y3FS0WJXOj1KaZTMeE03bsyCKAaEothJFkrYF8RyWVv7MpHXLXQ==";

    @GetMapping("/allApi")
    public String getAll() {
        WebClient webClient = WebClient.create();
        String responseJson = webClient.get()
                .uri("http://192.168.11.54:8080/api/")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return responseJson;
    }


    @GetMapping("/ProxGetTick")
    public ProxmoxFirstAPIData ProxGetTick() throws SSLException {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", "root@pam");
        formData.add("password", "PC1835-inv");

        ProxmoxFirstAPIData response = webClient
                .post()
                .uri("https://172.16.3.2:8006/api2/json/access/ticket")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .exchange()
                .block()
                .bodyToMono(ProxmoxFirstAPIData.class)
                .block();



        return response;
    }

    @GetMapping("/getProxData")
    public String getProxData() throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        WebClient webClient = WebClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.github.v3+json")
                .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        String responseJson = webClient.get()
                .uri("https://172.16.3.2:8006/api2/json/cluster/status")
                .cookie("PVEAuthCookie", cookieValue)
                .header("CSRFPreventionToken", "63FB3287:YsIdhaG0yBsO8EwlfM+lI+1DnfI5z4/e1dLAuii6Zi8")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return responseJson;
    }
}
