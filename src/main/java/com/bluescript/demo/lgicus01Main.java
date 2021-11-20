package com.bluescript.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class lgicus01Main {

    public static void main(String[] args) {
        SpringApplication.run(lgicus01Main.class, args);
    }

    // @Bean
    // public WebClient webClientBuilder() {
    // // WebClient client =
    // return WebClient.builder().baseUrl("http://localhost:8080").defaultCookie("cookieKey", "cookieValue")
    // .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    // // .defaultUriVariables(Collections.singletonMap("url",
    // // "http://localhost:8080"))
    // .build();
    // }

}
