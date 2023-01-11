package com.bosch.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository cuExchangeRepository;
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		CurrencyExchange currencyExchangeService = cuExchangeRepository.findByFromAndTo(from, to);
		
		if(currencyExchangeService == null) {
			throw new RuntimeException("Data Not Found");
		}
		
//		CurrencyExchange currencyExchangeService = new CurrencyExchange(100, "USD", "INR", BigDecimal.valueOf(86));
		
		String currentEnvInstance = "local.server.port";
		currencyExchangeService.setEnvironment(environment.getProperty(currentEnvInstance));
		
		return currencyExchangeService;
		
	} 
}
