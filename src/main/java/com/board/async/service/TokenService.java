package com.board.async.service;

import com.board.async.model.token.TokenHeader;
import com.board.async.model.token.TokenPayload;
import com.board.async.model.token.enums.SignAlgorithm;
import com.board.async.model.token.enums.SignKey;
import com.board.async.service.token.AlgorithmFactory;
import com.board.async.service.token.SignAlg;
import com.board.async.service.token.SignKeyFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AlgorithmFactory algFactory;

    @Autowired
    private SignKeyFactory signKeyFactory;

    public String createToken(SignAlgorithm alg, TokenPayload token) {
        try {
            String header = Base64.encode(objectMapper.writeValueAsBytes(ImmutableMap.of("alg", alg, "typ", "JWT")));
            String payload = Base64.encode(objectMapper.writeValueAsBytes(token));

            String document = concat(".", header, payload);
            PrivateKey pvKey = signKeyFactory.getSignPrivateKey(alg);
            SignAlg algorithm = algFactory.getSignAlgorithm(alg);

            String signature = Base64.encode(algorithm.sign(pvKey, document.getBytes()));
            return concat(".", header, payload, signature);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public TokenPayload verifyToken(String jwt) {
        try {
            String[] jwtArray = jwt.split("\\.");

            TokenHeader header = objectMapper.readValue(Base64.decode(jwtArray[0]), TokenHeader.class);
            TokenPayload payload = objectMapper.readValue(Base64.decode(jwtArray[1]), TokenPayload.class);

            PublicKey pbKey = signKeyFactory.getSignPublicKey(header.getAlg());
            SignAlg algorithm = algFactory.getSignAlgorithm(header.getAlg());
            return Optional.of(algorithm.verify(pbKey, concat(".", jwtArray[0], jwtArray[1]).getBytes(), Base64.decode(jwtArray[2])))
                    .filter(result -> result)
                    .map((result) -> payload)
                    .orElse(null);

        } catch (Exception e) {
            return null;
        }
    }

    private String concat(String split, String... data) {
        return Arrays.stream(data)
                .reduce((a, b) -> a + split + b)
                .orElse("");
    }

}
