package com.example.monitor_cotacoes;

import com.example.monitor_cotacoes.entity.Alerta;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonitorCotacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorCotacoesApplication.class, args);
	}


}
