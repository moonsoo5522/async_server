package com.board.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;

public class AsyncApplicationTests {

    @Test
    public void contextLoads() throws InterruptedException {
        Flux<String> just = Flux.just("ffws", "tlqkf", "rndhkdkr").subscribeOn(Schedulers.newSingle("ff")).log();
        just.subscribe((data) -> {
            System.out.println(data + "dd");
        });

        ArrayWrraper wrraper = () -> new ArrayList<String>();
        Thread.sleep(1000);
    }

}
