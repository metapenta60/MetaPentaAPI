package com.uniandes.metapenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class MetapentaApplication {
	public static void main(String[] args) {
		SpringApplication.run(MetapentaApplication.class, args);
	}

	@Bean(name = "customRestTemplate")
	public RestTemplate customRestTemplate() {
		return new RestTemplate();
	}
}
