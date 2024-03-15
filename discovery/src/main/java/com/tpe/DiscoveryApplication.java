package com.tpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//!!! Server olarak kullanılacağını göstermek için @EnableEurekaServer kullanılması gerekir. Bu annotation Spring Boot uygulamasını Eureka sunucusu olarak yapılandırmak için kullanılır.
public class DiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApplication.class, args);
	}

}