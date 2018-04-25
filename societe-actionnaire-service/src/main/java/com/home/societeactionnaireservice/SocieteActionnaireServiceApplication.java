package com.home.societeactionnaireservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.home.societeactionnaireservice.repositories.ActionnaireRepository;

@EnableEurekaClient
@SpringBootApplication
public class SocieteActionnaireServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SocieteActionnaireServiceApplication.class, args);
	}

	@Autowired
	private ActionnaireRepository actionnaireRepository;

	@Override
	public void run(String... args) throws Exception {
		
		
	}
}
