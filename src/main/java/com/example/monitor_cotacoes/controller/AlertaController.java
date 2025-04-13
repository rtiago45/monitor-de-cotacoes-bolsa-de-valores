package com.example.monitor_cotacoes.controller;


import com.example.monitor_cotacoes.dto.AlertaRequestDTO;
import com.example.monitor_cotacoes.entity.Alerta;
import com.example.monitor_cotacoes.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @PostMapping
    public ResponseEntity<Alerta> criarAlerta(@Valid @RequestBody AlertaRequestDTO dto) {
        Alerta alerta = alertaService.criar(dto);
        return ResponseEntity.ok(alerta);
    }

    @GetMapping
    public ResponseEntity<List<Alerta>> listarTodos() {
        return ResponseEntity.ok(alertaService.listarTodos());
    }
}

