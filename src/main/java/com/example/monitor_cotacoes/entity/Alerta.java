package com.example.monitor_cotacoes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alertas")
@Getter
@Setter
@NoArgsConstructor
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;

    @Column(name = "preco_alvo", nullable = false)
    private Double precoAlvo;

    @Column(name = "numero_whatsapp", nullable = false)
    private String numeroWhatsapp;

    private Boolean ativo;
}
