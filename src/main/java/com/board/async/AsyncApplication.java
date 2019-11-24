package com.board.async;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.client.RestTemplate;

@EnableReactiveMongoRepositories(basePackages = "com.board.async.repository")
@SpringBootApplication
public class AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

    @Bean
    public SimpleReactiveMongoDatabaseFactory boardFactory(MongoClient mongoClient) {
        return new SimpleReactiveMongoDatabaseFactory(mongoClient, "test");
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(@Qualifier("boardFactory") ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory, MongoConverter converter) {
        return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory, converter);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
