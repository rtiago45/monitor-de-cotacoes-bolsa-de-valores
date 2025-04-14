package com.example.monitor_cotacoes.controller;

import com.example.monitor_cotacoes.service.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
public class MonitorController {

    private final AlertaService alertaService;

    @PostMapping("/verificar")
    public ResponseEntity<Void> verificarCotas() {
        alertaService.listarAtivos();
        return ResponseEntity.ok().build();
    }
}
