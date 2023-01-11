package com.bosch.microservices.apigateway;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter{

	
	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		logger.info("Requested Path -> {}", exchange.getRequest().getPath());
//		for(Map.Entry<String, Object> map : exchange.getAttributes().entrySet()) {
//			if(map.getKey().contains("gatewayRequestUrl") && map.getValue().toString().contains("currency-conversion")) {
//			return null;
//			}
//		}
		
		
		return chain.filter(exchange);
	} 

}
