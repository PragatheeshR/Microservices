package com.bosch.microservices.springclougconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringClougConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClougConfigServerApplication.class, args);
	}

}
