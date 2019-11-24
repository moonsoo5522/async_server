package com.board.async.handler;

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

import java.util.HashMap;

@Component
public class HomeHandler {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Mono<ServerResponse> hello(ServerRequest request) {
        System.out.println(memberRepository.findByDeviceId("hello").getRegDt());
        return ServerResponse.ok()
                  .body(boardRepository.findAll(), Board.class);
    }

    public Mono<ServerResponse> test(ServerRequest request) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        return ServerResponse.ok().body(Mono.just("ok"), String.class);
    }
}
