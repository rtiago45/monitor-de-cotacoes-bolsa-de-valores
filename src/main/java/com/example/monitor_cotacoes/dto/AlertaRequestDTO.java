package com.example.monitor_cotacoes.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertaRequestDTO {

    @NotBlank(message = "O ticker é obrigatório")
    private String ticker;

    @NotNull(message = "O preço alvo é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private Double precoAlvo;

    @NotBlank(message = "O número do WhatsApp é obrigatório")
    private String numeroWhatsapp;
}
