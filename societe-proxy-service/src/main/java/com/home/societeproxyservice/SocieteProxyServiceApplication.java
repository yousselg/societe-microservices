package com.home.societeproxyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
@RefreshScope
@EnableFeignClients("com.home.societeproxyservice")
public class SocieteProxyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocieteProxyServiceApplication.class, args);
	}
	
	@Bean
	public AlwaysSampler defaulSampler(){
		return new AlwaysSampler();
	}
}

