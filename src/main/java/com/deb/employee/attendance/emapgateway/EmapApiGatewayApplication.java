package com.deb.employee.attendance.emapgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

@SpringBootApplication
@EnableDiscoveryClient
public class EmapApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmapApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator enableCustomRoutingForEmap(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("login_route", r -> r.path("/authenticate")
				.uri("http://localhost:8082/authenticate"))
				.build();
	}

	@Bean
	public CorsWebFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedMethod("*");
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
		source.registerCorsConfiguration("/**",config);
		return new CorsWebFilter(source);
	}

}
