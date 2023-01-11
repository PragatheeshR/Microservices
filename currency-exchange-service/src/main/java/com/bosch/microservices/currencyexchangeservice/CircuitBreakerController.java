package com.bosch.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/praga")
	//@Retry(name="praga", fallbackMethod = "dummyResponse")
	@CircuitBreaker(name="default", fallbackMethod = "dummyResponse")
	//@RateLimiter(name="default") // use case: to set number of calls per minute or time based
	@Bulkhead(name="default") //use case: to set how many concurrent calls can be made.
	public String sampleCB() {
		logger.info("Sample CB call ->");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8001/praga", String.class);
//		
		return forEntity.getBody();
//		return "sample-api";
	}
	
	public String dummyResponse(Exception e) {
		ResponseEntity<String> forEntity = new ResponseEntity<String>("FAILED AFTER n ATTEMPTS", HttpStatus.REQUEST_TIMEOUT);
		return forEntity.getBody();
	}
}
