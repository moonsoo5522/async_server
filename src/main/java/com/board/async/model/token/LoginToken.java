package com.board.async.model.token;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginToken {

    private String token;
}
