package com.home.societeproxyservice.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.home.societeproxyservice.services.SocieteServiceProxy;

@RestController
@RequestMapping(value = "/proxy", produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
public class ProxyController {

	private static final Logger logger = LoggerFactory.getLogger(ProxyController.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SocieteServiceProxy societeServiceProxy;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@GetMapping
	public Resources<Societe> getSocieteNames() {
		ParameterizedTypeReference<Resources<Societe>> responseType = new ParameterizedTypeReference<Resources<Societe>>() {
		};
		return restTemplate.exchange("http://societe-service/societes", HttpMethod.GET, null, responseType).getBody();

	}

	@GetMapping("/feign")
	public Resources<Societe> getSocieteNamesFeign() {
		return societeServiceProxy.getSocietes();
	}

	@GetMapping("/feignlist")
	public List<Societe> getSocieteNamesFeignList() {
		return societeServiceProxy.getSocietesFeign();
	}
	
	@GetMapping("/feignlistport")
	public Map<String,List<Societe>> getSocieteNamesFeignListWithPort() {
		Map<String,List<Societe>> result = societeServiceProxy.getSocietesFeignWithPort();
		logger.info("******************* " +result.values().size()+ " object selected");
		return result;
	}

	@PostMapping
	public Societe addSociete(@RequestBody Societe societe) {
		return societe;
	}

}
