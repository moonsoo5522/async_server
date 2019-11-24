package com.board.async.service;

import com.board.async.model.token.DeviceLogin;
import com.board.async.model.token.LoginToken;
import com.board.async.model.token.TokenPayload;
import com.board.async.model.token.enums.SignAlgorithm;
import com.board.async.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeviceLoginService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private TokenService tokenService;

    public Mono<LoginToken> login(Mono<DeviceLogin> body) {
        return body.map(data -> repository.findByDeviceId(data.getDeviceId()))
                .map(member -> createToken(member.getDeviceId()))
                .doOnError((e) -> {
                    throw new RuntimeException("디바이스아이디 없음");
                });
    }


    private LoginToken createToken(String deviceId) {
        return LoginToken.builder().token(
                tokenService.createToken(SignAlgorithm.ES256, TokenPayload.builder().deviceId(deviceId).build())).build();
    }
}
