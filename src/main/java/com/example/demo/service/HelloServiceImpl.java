package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.persistence.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HelloServiceImpl implements HelloService{
    private final MemberRepository memberRepository;

    @Override
    public String sayHello(String name){
        return "hello " + name;
    }

    @Override
    public String saveMember(Member member){
        memberRepository.save(member);
        return "ok";
    }

    @Override
    public Member findById(Long id){
        return memberRepository.findById(id);
    }
}
