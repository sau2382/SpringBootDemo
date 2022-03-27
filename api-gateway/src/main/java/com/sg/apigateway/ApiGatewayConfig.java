package com.sg.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("Header1", "dummyHeader")
								.addRequestParameter("Param", "dummyParam"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("**/test*")
						.uri("lb://DEMO-SERVICE"))
				.build();
	}

}
