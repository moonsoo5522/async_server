package com.board.async.service;

import com.board.async.model.Member;
import com.board.async.model.request.JoinRequest;
import com.board.async.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Mono<Member> getMemberByNo(long memNo) {
        return Mono.fromCallable(() -> memberRepository.findByMemNo(memNo));
    }

    public Mono<Void> addMember(Mono<JoinRequest> joinRequest) {
        return joinRequest.map(data -> {
            Member member = new Member();
            member.setAgeRange(data.getAgeRange());
            member.setDeviceId(data.getDeviceId());
            member.setGender(data.getGender());
            member.setNationCd(data.getNationCd());
            member.setRegDt(new Date());

            memberRepository.save(member);
            return null;
        });
    }
}
