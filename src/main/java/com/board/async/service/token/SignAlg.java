package com.board.async.service.token;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface SignAlg {

    byte[] sign(PrivateKey pvkey, byte[] payload);

    boolean verify(PublicKey pbk, byte[] payload, byte[] signature);
}
