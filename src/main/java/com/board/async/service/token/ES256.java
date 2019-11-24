package com.board.async.service.token;

import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

@Component
public class ES256 implements SignAlg {
    @Override
    public byte[] sign(PrivateKey pvkey, byte[] payload) {
        try {
            Signature sig = Signature.getInstance("SHA256WithECDSA");
            sig.initSign(pvkey);
            sig.update(payload);
            return sig.sign();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean verify(PublicKey pbk, byte[] payload, byte[] signature) {
        try {
            Signature sig = Signature.getInstance("SHA256WithECDSA");
            sig.initVerify(pbk);
            sig.update(payload);
            return sig.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
