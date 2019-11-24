package com.board.async;

import com.board.async.model.token.TokenHeader;
import com.board.async.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;


@RunWith(SpringRunner.class)
@TestPropertySource({"classpath:application.properties", "classpath:application-local.properties"})
@SpringBootTest
public class AsyncApplicationTests {

    @Autowired
    private TokenService tokenService;

    @Test
    public void contextLoads() throws InterruptedException {
        Flux<String> just = Flux.just("ffws", "tlqkf", "rndhkdkr")
                .publishOn(Schedulers.newSingle("ffff"))
                .subscribeOn(Schedulers.newSingle("ff")).log();
        just.subscribe((data) -> {
            System.out.println(data + "dd");
        });

        ArrayWrraper wrraper = () -> new ArrayList<String>();
        Thread.sleep(100000);
    }

    @Test
    public void test() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();

        for(int i=0; i<5; i++) {
       //     ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:7000/test", String.class);
        //    System.out.println(response.getBody());
        }

        System.out.println("request start! ");
        for(int i=0; i<5; i++) {
            Mono<ClientResponse> response = WebClient.create("http://localhost:7000/test").get().exchange().log();
            response.subscribe((data) -> {
                System.out.println("resonse code " + data.rawStatusCode());
            });
        }

        Thread.sleep(5000);
    }

    @Test
    public void test2() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode("MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCA3yX1C1z34remV/UaVZjJwJNwGmq3ZSWJJanpPgAlLvg=="));
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        keyFactory.generatePrivate(spec);
    }

    @Test
    public void keyPairGenerate() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp256r1"), new SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();
        byte[] privateKeyS = privateKey.getEncoded();
        byte[] publicKeyX = publicKey.getW().getAffineX().toByteArray();
        byte[] publicKeyY = publicKey.getW().getAffineY().toByteArray();
        String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKeyS);
        String encodedPublicKeyX = Base64.getEncoder().encodeToString(publicKeyX);
        String encodedPublicKeyY = Base64.getEncoder().encodeToString(publicKeyY);
        System.out.println("encodedPrivateKey = " + encodedPrivateKey);
        System.out.println("encodedPublicKeyX = " + encodedPublicKeyX);
        System.out.println("encodedPublicKeyY = " + encodedPublicKeyY);
        System.out.println("encodedPublicKey = " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
    }

    @Test
    public void jsontest() throws IOException {
        tokenService.verifyToken("eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXZpY2VJZCI6ImhlbGxvIn0=.MEYCIQDdd9nSYcOSq/dJRYZeCJcu0LIFB0/luDfZQwBdlphEMQIhALWfdW0hiex9t3Pe4cOY3vhI0qfqLk2oIzGbiBUqIV7r");
    }
}
