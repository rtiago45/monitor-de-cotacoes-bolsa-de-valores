package com.example.monitor_cotacoes.service;

import com.example.monitor_cotacoes.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final TwilioConfig twilioConfig;

    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        // Inicializa o Twilio com os dados do application.properties
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    /**
     * Envia uma mensagem via WhatsApp para o número informado.
     *
     * @param to    Número do WhatsApp do usuário (ex: +551199999999)
     * @param ticker Código da ação
     * @param preco  Preço atual da ação
     */
    public void enviarAlerta(String to, String ticker, Double preco) {
        String mensagem = "📈 Alerta de Ação!\n" +
                "O ativo *" + ticker + "* atingiu o preço de *R$ " + preco + "*.";

        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(twilioConfig.getFromNumber()),
                mensagem
        ).create();
    }
}
