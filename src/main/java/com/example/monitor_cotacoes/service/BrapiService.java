package com.example.monitor_cotacoes.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrapiService {

    @Value("${brapi.token}")
    private String brapiToken;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public BrapiService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Consulta a cotação atual da ação a partir da API da Brapi.
     * @param ticker Código do ativo, exemplo: "PETR4"
     * @return Preço atual da ação ou null se ocorrer algum erro.
     */
    public Double buscarPreco(String ticker) {
        String url = "https://brapi.dev/api/quote/" + ticker + "?token=" + brapiToken;
        try {
            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);
            return root.path("results").get(0).path("regularMarketPrice").asDouble();
        } catch (Exception e) {
            // Aqui você pode logar o erro se necessário
            e.printStackTrace();
            return null;
        }
    }
}
