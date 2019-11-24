package com.board.async.service.token;

import com.board.async.model.token.enums.SignAlgorithm;
import com.board.async.model.token.enums.SignKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class SignKeyFactory {

    @Value("${jwt.ec.privatekey}")
    private String ecPrivateKey;

    @Value("${jwt.ec.publickey}")
    private String ecPublicKey;


    public PrivateKey getSignPrivateKey(SignAlgorithm alg) {
        try {
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(ecPrivateKey));
            KeyFactory keyFactory = KeyFactory.getInstance(alg.getKeyAlg(), "BC");
            return keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            return null;
        }
    }

    public PublicKey getSignPublicKey(SignAlgorithm alg) {
        try {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(ecPublicKey));
            KeyFactory keyFactory = KeyFactory.getInstance(alg.getKeyAlg(), "BC");
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            return null;
        }
    }
}
