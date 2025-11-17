package com.cingo.moedas_devs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MoedasDevsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoedasDevsApplication.class, args);
	}

}
