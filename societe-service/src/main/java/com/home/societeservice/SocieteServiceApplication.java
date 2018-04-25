package com.home.societeservice;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

import com.home.societeservice.entities.Societe;
import com.home.societeservice.repositories.SocieteRepository;

@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
public class SocieteServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SocieteServiceApplication.class, args);
	}

	@Bean
	public AlwaysSampler defaulSampler() {
		return new AlwaysSampler();
	}

	@Autowired
	private SocieteRepository societeRepository;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 80; i++)
			Stream.of("Casa ", "Prestashop", "IBM", "ATOS", "Cppa", "bek")
					.forEach(s -> societeRepository.save(new Societe(s)));
	}
}
