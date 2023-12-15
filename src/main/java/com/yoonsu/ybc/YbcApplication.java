package com.yoonsu.ybc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.yoonsu.ybc.*.entity")
@EnableJpaRepositories("com.yoonsu.ybc.*.repository")
@SpringBootApplication
public class YbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(YbcApplication.class, args);
	}

}
