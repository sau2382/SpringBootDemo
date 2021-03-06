package com.sg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceDemoApplication.class, args);
	}

}
