package com.example.monitor_cotacoes.service;

import com.example.monitor_cotacoes.dto.AlertaRequestDTO;
import com.example.monitor_cotacoes.entity.Alerta;
import com.example.monitor_cotacoes.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;

    public AlertaService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    public Alerta criar(AlertaRequestDTO dto) {
        Alerta alerta = new Alerta();
        alerta.setTicker(dto.getTicker().toUpperCase());
        alerta.setPrecoAlvo(dto.getPrecoAlvo());
        alerta.setNumeroWhatsapp(dto.getNumeroWhatsapp());
        alerta.setAtivo(true);

        return alertaRepository.save(alerta);
    }

    public List<Alerta> listarAtivos() {
        return alertaRepository.findByAtivoTrue();
    }

    public void desativar(Alerta alerta) {
        alerta.setAtivo(false);
        alertaRepository.save(alerta);
    }

    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }
}
