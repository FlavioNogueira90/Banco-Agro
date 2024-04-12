package br.com.bancoAgro.apigateway;

import metrics.ExporMetricas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"metrics"})
public class ApiGatewayApplication {
	@Autowired
	ExporMetricas exporMetricas;
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
						//.uri("lb://SRV-CONTA-CORRENTE")
						.uri("http://10.103.28.58:8081")
				)
				.route(p -> p
						.path("/api/srv-abertura-conta/**")
						.filters(f -> f
								.stripPrefix(2))
						//.uri("lb://SRV-ABERTURA-CONTA")
						.uri("http://10.109.211.242:8087")
				)
				.route(p -> p
						.path("/api/srv-cadastro-cliente/**")
						.filters(f -> f
								.stripPrefix(2))
						//.uri("lb://SRV-CADASTRO-CLIENTE")
						.uri("http://10.101.16.130:8082")
				).build();
	}


}
