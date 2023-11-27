package com.br.digital_hoteis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = "com.br.digital_hoteis.domain.repository")
//
//@EnableJpaAuditing
//@EntityScan("com.br.digital_hoteis.domain.entity")
@EnableJpaRepositories
@SpringBootApplication
public class DigitalHoteisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalHoteisApplication.class, args);
	}

}
