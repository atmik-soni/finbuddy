
package com.example.ollama.service;

import com.example.ollama.model.OllamaChatRequest;
import com.example.ollama.model.OllamaRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OllamaService {

    private static final String GENERATE_URL = "http://localhost:11434/api/generate";
    private static final String CHAT_URL = "http://localhost:11434/api/chat";

    public String generate(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        OllamaRequest request = new OllamaRequest("mistral", prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OllamaRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(GENERATE_URL, entity, String.class);

        return response.getBody();
    }

    public String chat(List<OllamaChatRequest.Message> messages) {
        RestTemplate restTemplate = new RestTemplate();
        OllamaChatRequest chatRequest = new OllamaChatRequest("mistral", messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OllamaChatRequest> entity = new HttpEntity<>(chatRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(CHAT_URL, entity, String.class);

        return response.getBody();
    }
}
