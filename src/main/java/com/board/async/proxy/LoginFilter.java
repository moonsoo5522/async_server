package com.board.async.proxy;

import com.board.async.annotation.AuthRequired;
import com.board.async.model.token.TokenPayload;
import com.board.async.service.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.result.method.RequestMappingInfo;
import org.springframework.web.reactive.result.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Aspect
public class LoginFilter {

    @Autowired
    private TokenService tokenService;

    @Around("@annotation(com.board.async.annotation.AuthRequired)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServerRequest serverRequest = ServerRequest.class.cast(proceedingJoinPoint.getArgs()[0]);
        ServerRequest.Headers headers = serverRequest.headers();

        List<String> ltokenList = headers.header("ltoken");
        if(CollectionUtils.isEmpty(ltokenList)) {
            // error return
        }

        String lotken = ltokenList.get(0);
        TokenPayload payload = tokenService.verifyToken(lotken);

        serverRequest.attributes().put("ltoken", payload);
        return proceedingJoinPoint.proceed();
    }
}
