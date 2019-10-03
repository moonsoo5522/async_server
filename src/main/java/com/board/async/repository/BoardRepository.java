package com.board.async.repository;

import com.board.async.model.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BoardRepository extends ReactiveMongoRepository<Board, String> {
    Flux<Board> findByName(String name);
}
