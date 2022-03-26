package io.github.revisit_app.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()

				// User profile service
				.route("get-user-profile", routeSpec -> routeSpec
						.path("/users/**")
						.filters(fs -> fs.rewritePath("/users/(?<username>.*)", "/${username}"))
						.uri("http://localhost:8085/"))

				// Decks service
				.route("get-deck", routeSpec -> routeSpec
						.path("/decks/**")
						.filters(fs -> fs.rewritePath("/decks/(?<deckId>.*)", "/${deckId}"))
						.uri("http://localhost:8082/"))
				.route("create-deck", routeSpec -> routeSpec
						.path("/decks")
						.and()
						.method(HttpMethod.POST)
						.uri("http://localhost:8082/"))
				.route("update-deck", routeSpec -> routeSpec
						.path("/decks")
						.and()
						.method(HttpMethod.PUT)
						.filters(fs -> fs.rewritePath("/decks/(?<deckId>.*)", "/${deckId}"))
						.uri("http://localhost:8082/"))
				.route("delete-deck", routeSpec -> routeSpec
						.path("/decks")
						.and()
						.method(HttpMethod.DELETE)
						.filters(fs -> fs.rewritePath("/decks/(?<deckId>.*)", "/${deckId}"))
						.uri("http://localhost:8082/"))
				.route("save-deck", routeSpec -> routeSpec
						.path("/decks/saved")
						.and()
						.method(HttpMethod.PUT)
						.filters(fs -> fs.rewritePath("/decks/saved/(?<deckId>.*)", "/saved/${deckId}"))
						.uri("http://localhost:8082/"))
				.route("remove-deck", routeSpec -> routeSpec
						.path("/decks/saved")
						.and()
						.method(HttpMethod.DELETE)
						.filters(fs -> fs.rewritePath("/decks/saved/(?<deckId>.*)", "/saved/${deckId}"))
						.uri("http://localhost:8082/"))
				.route("get-saved-decks", routeSpec -> routeSpec
						.path("/decks/saved")
						.and()
						.method(HttpMethod.GET)
						.uri("http://localhost:8082/"))

				// Cards service
				.route("get-card", routeSpec -> routeSpec
						.path("/cards/**")
						.filters(fs -> fs.rewritePath("/cards/(?<cardId>.*)", "/${cardId}"))
						.uri("http://localhost:8081/"))
				.route("create-card", routeSpec -> routeSpec
						.path("/cards")
						.and()
						.method(HttpMethod.POST)
						.uri("http://localhost:8081/"))
				.route("update-card", routeSpec -> routeSpec
						.path("/cards")
						.and()
						.method(HttpMethod.PUT)
						.filters(fs -> fs.rewritePath("/cards/(?<cardId>.*)", "/${cardId}"))
						.uri("http://localhost:8081/"))
				.route("delete-card", routeSpec -> routeSpec
						.path("/cards")
						.and()
						.method(HttpMethod.DELETE)
						.filters(fs -> fs.rewritePath("/cards/(?<cardId>.*)", "/${cardId}"))
						.uri("http://localhost:8081/"))
				.route("get-saved-cards", routeSpec -> routeSpec
						.path("/cards/saved")
						.and()
						.method(HttpMethod.GET)
						.uri("http://localhost:8081/"))
				.route("save-card", routeSpec -> routeSpec
						.path("/cards/saved")
						.and()
						.method(HttpMethod.PUT)
						.filters(fs -> fs.rewritePath("/cards/saved/(?<cardId>.*)", "/saved/${cardId}"))
						.uri("http://localhost:8081/"))
				.route("remove-card", routeSpec -> routeSpec
						.path("/cards/saved")
						.and()
						.method(HttpMethod.DELETE)
						.filters(fs -> fs.rewritePath("/cards/saved/(?<cardId>.*)", "/saved/${cardId}"))
						.uri("http://localhost:8081/"))
				.build();
	}
}
