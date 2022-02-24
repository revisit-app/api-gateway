package io.github.revisit_app.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class GreetingController {
  
  @GetMapping("/hello")
  public Mono<String> greet() {
    return Mono.just("Hello World!");
  } 
}
