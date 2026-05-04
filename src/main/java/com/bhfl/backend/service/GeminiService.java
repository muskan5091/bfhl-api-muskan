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
                "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                + apiKey;

            Map<String, Object> payload = Map.of(
                "contents", List.of(
                    Map.of(
                        "parts", List.of(
                            Map.of(
                                "text",
                                question + ". Answer in one word only."
                            )
                        )
                    )
                )
            );

            Map response = restTemplate.postForObject(apiUrl, payload, Map.class);

            
            List candidates = (List) response.get("candidates");
            Map first = (Map) candidates.get(0);
            Map content = (Map) first.get("content");
            List parts = (List) content.get("parts");
            String text = (String) ((Map) parts.get(0)).get("text");

            return text.trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI Error";
        }
    }
}
