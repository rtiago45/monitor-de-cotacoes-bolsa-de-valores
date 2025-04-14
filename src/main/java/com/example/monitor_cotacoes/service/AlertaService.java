package com.example.monitor_cotacoes.service;

import com.example.monitor_cotacoes.dto.AlertaRequestDTO;
import com.example.monitor_cotacoes.entity.Alerta;
import com.example.monitor_cotacoes.repository.AlertaRepository;
import com.example.monitor_cotacoes.service.BrapiService;
import com.example.monitor_cotacoes.service.TwilioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final BrapiService brapiService;
    private final TwilioService twilioService;

    public AlertaService(AlertaRepository alertaRepository, BrapiService brapiService, TwilioService twilioService) {
        this.alertaRepository = alertaRepository;
        this.brapiService = brapiService;
        this.twilioService = twilioService;
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

    public void verificarEEnviarAlertas() {
        List<Alerta> alertasAtivos = alertaRepository.findByAtivoTrue();
        for (Alerta alerta : alertasAtivos) {
            Double precoAtual = brapiService.buscarPreco(alerta.getTicker());
            if (precoAtual != null && precoAtual >= alerta.getPrecoAlvo()) {
                twilioService.enviarAlerta(alerta.getNumeroWhatsapp(), alerta.getTicker(), precoAtual);
                alerta.setAtivo(false); // desativa após envio
                alertaRepository.save(alerta);
            }
        }
    }

    public List<Alerta> listarTodos() {
        return alertaRepository.findAll();
    }
}
