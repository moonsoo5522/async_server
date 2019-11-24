package com.board.async.model.token;


import com.board.async.model.token.enums.SignAlgorithm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenHeader {

    private SignAlgorithm alg;

    private String typ;
}
