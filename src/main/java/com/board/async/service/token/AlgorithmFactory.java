package com.board.async.service.token;

import com.board.async.model.token.enums.SignAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmFactory {

    @Autowired
    private ES256 es256;

    public SignAlg getSignAlgorithm(SignAlgorithm alg) {
        if(alg == SignAlgorithm.ES256) {
            return es256;
        } else {
            return null;
        }
    }
}
