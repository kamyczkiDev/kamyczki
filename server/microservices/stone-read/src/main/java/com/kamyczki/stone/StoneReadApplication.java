package com.kamyczki.stone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableJpaRepositories
@SpringBootApplication()
public class StoneReadApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoneReadApplication.class, args);
	}
}
