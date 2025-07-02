package com.shaon.declarative_crud.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;


@Configuration
public class HelloRouter {


    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route().path("/api/v1",builder -> builder.GET("/get-message",accept(MediaType.TEXT_PLAIN),request -> ServerResponse.ok().bodyValue("Hello World!"))).build();
    }
}
