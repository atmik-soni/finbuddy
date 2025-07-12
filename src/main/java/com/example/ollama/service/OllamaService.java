
package com.example.ollama.service;

import com.example.ollama.config.OllamaConfig;
import com.example.ollama.model.OllamaChatRequest;
import com.example.ollama.model.OllamaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OllamaService {

    @Autowired
    private OllamaConfig ollamaConfig;

    private static final String GENERATE_URL = "http://localhost:11434/api/generate";
    private static final String CHAT_URL = "http://localhost:11434/api/chat";

    public String generate(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        OllamaRequest request = new OllamaRequest(ollamaConfig.getModel(), prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OllamaRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(GENERATE_URL, entity, String.class);

        return response.getBody();
    }

    public String chat(List<OllamaChatRequest.Message> messages) {
        RestTemplate restTemplate = new RestTemplate();
        OllamaChatRequest chatRequest = new OllamaChatRequest(ollamaConfig.getModel(), messages);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OllamaChatRequest> entity = new HttpEntity<>(chatRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(CHAT_URL, entity, String.class);

        return response.getBody();
    }

    public String finbuddyPrompt(String userMessage) {
        String prompt = """
                You are FinBuddy, an AI assistant built to help small-town and rural users in India apply for PMMY (Pradhan Mantri Mudra Yojana) loans.

                The user may speak in English, Hindi, or Marathi. Detect the language and respond in the same language.

                Step 1: Understand the user's background
                Ask simple and polite questions to gather these details:
                - Name
                - Village/town, district, and state
                - Type of business or work (e.g. tailoring, dairy, kirana shop, service, trading, welding, etc.)
                - Daily or monthly income or wages
                - Whether they already run a business or are starting one
                - Mobile number
                - Aadhaar number (optional)

                Step 2: Based on income and business maturity, decide the PMMY loan category:
                - Shishu (for new businesses, up to ₹50,000)
                - Kishore (for growing businesses, ₹50,001 to ₹5 lakh)
                - Tarun (for established businesses, ₹5 lakh to ₹10 lakh)

                Explain the benefits of their matching category in their language (Marathi, Hindi, or English).

                Step 3: Generate a JSON object for their application draft:
                {
                  \"name\": \"\",
                  \"location\": \"\",
                  \"business_type\": \"\",
                  \"income\": \"\",
                  \"loan_category\": \"Shishu / Kishore / Tarun\",
                  \"loan_amount_requested\": \"\",
                  \"mobile\": \"\",
                  \"aadhaar_number\": \"\"
                }

                Only return the JSON and category name after all information is collected.
                Keep it friendly and local – like you're talking to a neighbor.

                User message: %s
                """.formatted(userMessage);

        return generate(prompt);
    }
    }
