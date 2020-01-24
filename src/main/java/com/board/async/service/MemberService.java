package com.board.async.service;

import com.board.async.model.Member;
import com.board.async.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Mono<Member> getMemberByNo(long memNo) {
        return Mono.fromCallable(() -> memberRepository.findByMemNo(memNo));
    }
}
