package com.board.async.model.token;

import com.board.async.service.TokenService;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class TokenPayload {

    private String deviceId;

    @JsonCreator
    public TokenPayload(String deviceId) {
        this.deviceId = deviceId;
    }
}
