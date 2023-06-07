package org.bosonit.ej3SpringBoot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Ej3SpringBootApplication implements CommandLineRunner {

	private final AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(Ej3SpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("name: {}", appConfig.getName());
		log.info("url: {}", appConfig.getUrl());
	}
}
