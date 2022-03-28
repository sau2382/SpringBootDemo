package com.sg.apigateway;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		Set<URI> uris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
		String originalUri = (uris.isEmpty()) ? "Unknown" : uris.iterator().next().toString();
		Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
		URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);

		logger.info("({} --> {} --> {}), (REQUEST: {}:{}), (ROUTE: {}, {}), (RESPONSE: {})",
				request.getRemoteAddress().getHostName() + ":" + request.getRemoteAddress().getPort(),
				request.getLocalAddress().getHostName() + ":" + request.getLocalAddress().getPort(),
				routeUri.getHost() + ":" + routeUri.getPort(), request.getMethod(), request.getURI(), originalUri,
				route.getId(), response.getStatusCode());

		// logger.info("Incoming request " + originalUri + " is routed to id: " +
		// route.getId() + ", uri:" + routeUri
		// + ", routeAttribute:");

		return chain.filter(exchange);
	}

}