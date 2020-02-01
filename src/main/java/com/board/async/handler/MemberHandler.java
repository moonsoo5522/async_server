package com.board.async.handler;

import com.board.async.model.request.JoinRequest;
import com.board.async.model.token.DeviceLogin;
import com.board.async.model.token.LoginToken;
import com.board.async.service.DeviceLoginService;
import com.board.async.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MemberHandler {

    @Autowired
    private DeviceLoginService dlService;

    @Autowired
    private MemberService memberService;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<DeviceLogin> body = request.bodyToMono(DeviceLogin.class);
        Mono<ServerResponse> body1 = ServerResponse.ok().body(dlService.login(body), LoginToken.class);
        return body1;
    }

    public Mono<ServerResponse> join(ServerRequest request) {
        Mono<JoinRequest> body = request.bodyToMono(JoinRequest.class);
        return ServerResponse.ok().body(memberService.addMember(body), Void.class);
    }
}
