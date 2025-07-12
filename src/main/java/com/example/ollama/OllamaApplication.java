package com.example.ollama;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class OllamaApplication {
    public static void main(String[] args) {

        SpringApplication.run(OllamaApplication.class, args);
    }
}
