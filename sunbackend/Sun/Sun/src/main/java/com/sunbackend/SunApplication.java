package com.sunbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SunApplication {
	public static void main(String[] args) {
		SpringApplication.run(SunApplication.class, args);
	}

}
