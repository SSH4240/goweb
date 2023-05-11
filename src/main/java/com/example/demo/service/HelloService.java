package com.example.demo.service;

import com.example.demo.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public interface HelloService {
    String sayHello(String name);
    String saveMember(Member member);
    Member findById(Long id);
}
