package com.board.async.handler;

import com.board.async.model.token.DeviceLogin;
import com.board.async.model.token.LoginToken;
import com.board.async.service.DeviceLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class MemberHandler {

    @Autowired
    private DeviceLoginService dlService;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<DeviceLogin> body = request.bodyToMono(DeviceLogin.class);
        Mono<ServerResponse> body1 = ServerResponse.ok().body(dlService.login(body), LoginToken.class);
        return body1;
    }
}
