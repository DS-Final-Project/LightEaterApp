package com.example.LightEaterApp.Chat.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/*
@RestController
public class FlaskController {
    private final WebClient webClient;

    public FlaskController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://172.20.10.6:5000").build();
    }

    @EventListener(ApplicationReadyEvent.class)
    public Mono<String> sendChatWords(String ChatWords) {
        Map<String, Object> chatWords = new HashMap<>();
        chatWords.put("chatWords", ChatWords);


        return webClient.method(HttpMethod.POST)
                .uri("/percentage")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(chatWords)
                .retrieve()
                .bodyToMono(Map.class)
                .map(result -> "resultNum: " + result.get("resultNum"));

    }
}

 */

