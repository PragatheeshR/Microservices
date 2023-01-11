package com.bosch.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosch.microservices.limitsservice.bean.Limits;
import com.bosch.microservices.limitsservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration config;
	
	@GetMapping("/limits")
	public Limits retreiveveLimits() {
		
		//return new Limits(100, 1000);
		return new Limits(config.getMinimum(), config.getMaximum());
	}

}
