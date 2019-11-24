package com.board.async;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.security.Security;

@Component
public class ServerRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
    }
}
