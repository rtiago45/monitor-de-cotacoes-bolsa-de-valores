package com.example.monitor_cotacoes.scheduler;

import com.example.monitor_cotacoes.entity.Alerta;
import com.example.monitor_cotacoes.service.AlertaService;
import com.example.monitor_cotacoes.service.BrapiService;
import com.example.monitor_cotacoes.service.TwilioService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlertaScheduler {

    private final AlertaService alertaService;
    private final BrapiService brapiService;
    private final TwilioService twilioService;

    public AlertaScheduler(AlertaService alertaService, BrapiService brapiService, TwilioService twilioService) {
        this.alertaService = alertaService;
        this.brapiService = brapiService;
        this.twilioService = twilioService;
    }

    /**
     * Verifica os alertas ativos a cada 5 minutos.
     * Se o preço atual da ação for menor ou igual ao preço-alvo,
     * dispara o alerta e desativa o alerta.
     */
    @Scheduled(fixedRate = 300000) // 5 minutos
    public void verificarAlertas() {
        List<Alerta> alertas = alertaService.listarAtivos();

        for (Alerta alerta : alertas) {
            Double precoAtual = brapiService.buscarPreco(alerta.getTicker());

            if (precoAtual != null && precoAtual <= alerta.getPrecoAlvo()) {
                twilioService.enviarAlerta(alerta.getNumeroWhatsapp(), alerta.getTicker(), precoAtual);
                alertaService.desativar(alerta);
            }
        }
    }
}
