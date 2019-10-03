package com.board.async;


import com.board.async.handler.HomeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@EnableWebFlux
@Configuration
public class WebConfig extends DelegatingWebFluxConfiguration {

    @Autowired
    HomeHandler homeHandler;

    @Bean
    public RouterFunction<?> boardRouter() {
        return route(GET("/").and(accept(APPLICATION_JSON)), homeHandler::hello);
    }
}