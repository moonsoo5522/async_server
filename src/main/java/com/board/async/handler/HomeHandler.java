package com.board.async.handler;

import com.board.async.annotation.AuthRequired;
import com.board.async.model.Board;
import com.board.async.model.Member;
import com.board.async.repository.BoardRepository;
import com.board.async.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;

@Component
public class HomeHandler {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok()
                  .body(Mono.just(memberRepository.findByDeviceId("hello")), Member.class);
    }

    public Mono<ServerResponse> test(ServerRequest request) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        return ServerResponse.ok().body(Mono.just("ok"), String.class);
    }

    @AuthRequired
    public Mono<ServerResponse> ping(ServerRequest request) {
        String ltoken = (String) request.attribute("ltoken").orElse("");
        System.out.println("outer : " + Thread.currentThread().getName());
        return ServerResponse.ok().body(Mono.just("hello")
                .publishOn(Schedulers.newElastic("pub")).log(), String.class);
    }
}
