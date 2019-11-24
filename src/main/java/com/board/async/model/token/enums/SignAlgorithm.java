package com.board.async.model.token.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SignAlgorithm {

    @JsonProperty(value = "RS256")
    RS256("RS256", "RSA"),

    @JsonProperty(value = "ES256")
    ES256("ES256", "ECDSA"),
    ;

    private final String signAlg;
    private final String keyAlg;

    SignAlgorithm(String signAlg, String keyAlg) {
        this.signAlg = signAlg;
        this.keyAlg = keyAlg;
    }

    public String getSignAlg() {
        return signAlg;
    }

    public String getKeyAlg() {
        return keyAlg;
    }
}
