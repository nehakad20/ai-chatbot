package com.example.aichatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class OpenAIService {

    @Value("${openai.api.key:}")
    private String apiKey;

    private final RestTemplate rest = new RestTemplate();

    public String getChatResponse(String userMessage) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            return "❌ OpenAI API key not configured. Please set it in application.properties.";
        }

        String url = "https://openrouter.ai/api/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Referer", "http://localhost:8080/");
        headers.set("X-Title", "Local AI Chatbot");



        Map<String, Object> system = new HashMap<>();
        system.put("role", "system");
        system.put("content", "You are a helpful AI assistant. Reply in short and simple sentences.");

        Map<String, Object> user = new HashMap<>();
        user.put("role", "user");
        user.put("content", userMessage);

        List<Map<String, Object>> messages = new ArrayList<>();
        messages.add(system);
        messages.add(user);

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "gpt-4o-mini");
        payload.put("messages", messages);
        payload.put("temperature", 0.5);
        payload.put("max_tokens", 300);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> resp = rest.postForEntity(url, entity, Map.class);
            Map body = resp.getBody();
            if (body == null) return "No response from OpenRouter.";

            List choices = (List) body.get("choices");
            if (choices == null || choices.isEmpty()) return "No choices returned.";

            Map first = (Map) choices.get(0);
            Map message = (Map) first.get("message");
            Object content = message.get("content");
            return content == null ? "Empty content." : content.toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
            return "⚠️ Error: " + e.getMessage();
        }
    }
}
