package com.example.client.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BenchmarkRestService {

    private final WebClient webClient;

    public BenchmarkRestService(WebClient webClient, @Value("${server.json.url:http://localhost:8080/api/rest}") String baseUrl) {
        this.webClient = webClient.mutate()
            .baseUrl(baseUrl)
            .build();
    }

    public long benchmarkRest(int sizeKb, int iterations) {
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            String response = webClient.get()
                    .uri(uriBuilder -> uriBuilder.queryParam("sizeKb", sizeKb).build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            // Optionally measure payload size: response.length()
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000; // ms
    }
}
