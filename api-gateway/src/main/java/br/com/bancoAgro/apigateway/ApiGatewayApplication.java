package br.com.bancoAgro.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.util.RouteMatcher;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder){

		return builder.routes()
				.route(p -> p
						.path("/api/srv-conta-corrente/**")
						.filters(f -> f
								.stripPrefix(2))
						.uri("lb://SRV-CONTA-CORRENTE")
				)
				.route(p -> p
						.path("/api/srv-abertura-conta/**")
						.filters(f -> f
								.stripPrefix(2))
						.uri("lb://SRV-ABERTURA-CONTA")
				)
				.route(p -> p
						.path("/api/srv-cadastro-cliente/**")
						.filters(f -> f
								.stripPrefix(2))
						.uri("lb://SRV-CADASTRO-CLIENTE")
				).build();
	}

}
