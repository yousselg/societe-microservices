package com.home.societeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/properties")
public class GetProperty {

	//@Value("${global}")
	private String value;

	@Autowired
	Environment environment;

	@GetMapping
	public String findByMe() {
		String port = environment.getProperty("local.server.port");
		return "Server port : " + port + "\n" + value;
	}

}
