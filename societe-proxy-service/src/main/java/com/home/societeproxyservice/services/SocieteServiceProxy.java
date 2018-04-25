package com.home.societeproxyservice.services;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;

import com.home.societeproxyservice.controller.Societe;

@FeignClient(name = "societe-service")
@RibbonClient(name = "societe-service")
public interface SocieteServiceProxy {

	@GetMapping("/societes")
	public Resources<Societe> getSocietes();
	
	@GetMapping("/societes/feign")
	public List<Societe> getSocietesFeign();
	
	@GetMapping("/societes/feign/port")
	public Map<String,List<Societe>> getSocietesFeignWithPort();

}
