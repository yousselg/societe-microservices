package com.home.societeservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.home.societeservice.entities.Societe;
import com.home.societeservice.repositories.SocieteRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
@RequestMapping("/societes")
public class SocietesController {

	private static final Logger logger = LoggerFactory.getLogger(SocietesController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private ApplicationContext appContext;

	@Autowired
	private SocieteRepository repository;

	// Add the produces field to avoid an error
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public Resources<Societe> getAllSocietes() {
		Resources<Societe> resource = new Resources<Societe>(repository.findAll());
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getOneSociete(null))
				.withRel("Get one societe by id"));
		return resource;
	}

	@GetMapping(value = "/rapport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ModelAndView getRepport() {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:/static/rapport/Silhouette.jrxml");
		view.setApplicationContext(appContext);
		Map<String, Object> params = new HashMap<>();
		params.put("datasource", this.getAllSocietesMap());
		return new ModelAndView(view, params);

	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public Resource<Societe> getOneSociete(@PathVariable("id") Long id) {
		Resource<Societe> resource = new Resource<Societe>(repository.findOne(id));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllSocietes())
				.withRel("Get one all societes"));
		return resource;
	}

	@GetMapping(value = "/feign")
	public List<Societe> getAll() {
		logger.info("Responder server port : " + environment.getProperty("local.server.port"));
		List<Societe> resource = repository.findAll();
		return resource;
	}

	@GetMapping(value = "/feign/port")
	public Map<String, List<Societe>> getAllWithPort() {
		logger.info("Responder server port : " + environment.getProperty("local.server.port"));
		List<Societe> resource = repository.findAll();
		logger.info(resource.size() + " objects selected");
		Map<String, List<Societe>> map = new HashMap<>();
		map.put(environment.getProperty("local.server.port"), resource);
		return map;
	}

	@GetMapping(value = "/feign/portfault")
	@HystrixCommand(fallbackMethod = "getAllWithPortFaultTolerenceFallback")
	public Map<String, List<Societe>> getAllWithPortFaultTolerence() {
		logger.info("Responder server port : " + environment.getProperty("local.server.port"));
		List<Societe> resource = repository.findAll();
		logger.info(resource.size() + " objects selected");
		Map<String, List<Societe>> map = new HashMap<>();
		map.put(environment.getProperty("local.server.port"), resource);
		return map;
	}

	public Map<String, List<Societe>> getAllWithPortFaultTolerenceFallback() {
		logger.info("Responder server port : " + environment.getProperty("local.server.port"));
		List<Societe> resource = repository.findAll();
		logger.info(resource.size() + " objects selected");
		Map<String, List<Societe>> map = new HashMap<>();
		map.put(environment.getProperty("local.server.port"), resource);
		return map;
	}

	private List<Map<String, Object>> getAllSocietesMap() {
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> map;
		for (Societe s : repository.findAll()) {
			map = new HashMap<>();
			map.put("id", s.getId().toString());
			map.put("rc", s.getRc());
			result.add(map);
		}
		return result;
	}

}
