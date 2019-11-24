package com.board.async;


import com.board.async.handler.HomeHandler;
import com.board.async.handler.MemberHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@EnableWebFlux
@Configuration
public class WebConfig extends DelegatingWebFluxConfiguration {

    @Autowired
    HomeHandler homeHandler;

    @Autowired
    private MemberHandler memberHandler;

    @Bean
    public RouterFunction<?> homeRouter() {
        return route(GET("/").and(accept(APPLICATION_JSON)), homeHandler::hello)
                .andRoute(GET("/test").and(accept(APPLICATION_JSON)), homeHandler::test);
    }

    @Bean
    public RouterFunction<?> memberRouter() {
        return route(POST("/login")
                    .and(accept(APPLICATION_JSON_UTF8))
                    .and(contentType(APPLICATION_JSON_UTF8)), memberHandler::login);
    }
}