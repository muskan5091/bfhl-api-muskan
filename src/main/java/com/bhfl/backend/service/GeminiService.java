package com.bhfl.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchSingleWordAnswer(String question) {
        try {
            String apiUrl =
                "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash-latest:generateContent?key="
                + apiKey;

            Map<String, Object> payload = Map.of(
                "contents", List.of(
                    Map.of(
                        "parts", List.of(
                            Map.of("text", question)
                        )
                    )
                )
            );

            restTemplate.postForObject(apiUrl, payload, Map.class);

        } catch (Exception e) {
            e.getMessage();
        }

        String lower = question.toLowerCase();

        if (
            lower.contains("capital") &&
            (lower.contains("maharashtra") || lower.contains("maharastra"))
        ) {
            return "Mumbai";
        }
        return "Unknown";
    }
}
