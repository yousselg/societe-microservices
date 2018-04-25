package com.home.societeactionnaireservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.societeactionnaireservice.entities.Actionnaire;
import com.home.societeactionnaireservice.repositories.ActionnaireRepository;

@RefreshScope
@RestController
@RequestMapping("/Actionnaires")
public class ActionnairesController {

	@Autowired
	private ActionnaireRepository repository;

	// Add the produces field to avoid an error
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public Resources<Actionnaire> getAllActionnaires() {
		Resources<Actionnaire> resource = new Resources<Actionnaire>(repository.findAll());
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getOneActionnaire(null))
				.withRel("Get one Actionnaire by id"));
		return resource;
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
	public Resource<Actionnaire> getOneActionnaire(@PathVariable("id") Long id) {
		Resource<Actionnaire> resource = new Resource<Actionnaire>(repository.findOne(id));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getAllActionnaires())
				.withRel("Get one all Actionnaires"));
		return resource;
	}

}
