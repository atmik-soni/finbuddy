
package com.example.ollama.controller;

import com.example.ollama.model.OllamaChatRequest;
import com.example.ollama.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ollama")
@CrossOrigin(origins = "*")
public class OllamaController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/ask")
    public String ask() {
        String prompt = "I earn 300rs daily as a farmers suggest microloan " +
                "Schemes for me and my family to improve our income suggest PMMY " +
                "government schemes for me and my family to improve our income";

        return ollamaService.generate(prompt);
    }

    @PostMapping("/chat")
    public String chat(@RequestBody List<OllamaChatRequest.Message> messages) {

        return ollamaService.chat(messages);
    }

    @PostMapping("/finbuddy")
    public String finbuddy(@RequestParam("message") String message) {
        return ollamaService.finbuddyPrompt(message);
    }
}
