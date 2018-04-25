package com.home.societeeurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@EnableEurekaServer
@SpringBootApplication
public class SocieteEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocieteEurekaServiceApplication.class, args);
	}
	
	@Bean
	public AlwaysSampler defaulSampler(){
		return new AlwaysSampler();
	}
}
