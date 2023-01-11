package com.bosch.microservices.apigateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI")
						.addRequestParameter("param", "Praga"))
						.uri("http://localhost:8080/limits"))
						.route(p -> p.path("/currency-exchange/**")
									 .uri("lb://currency-exchange")) // lb means load balancing
						.route(p -> p.path("/currency-conversion/**")
									 .uri("lb://currency-conversion"))
						.route(p -> p.path("/currency-conversion-feign/**")
								 .uri("lb://currency-conversion"))
						.route(p -> p.path("/pragatheesh/**")
									 .filters(f -> f.rewritePath("/pragatheesh/(?<segment>.*)", "/currency-conversion-feign/${segment}"))
								 .uri("lb://currency-conversion"))
						.build();
		
	}
}
