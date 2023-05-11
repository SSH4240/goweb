package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.HelloService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HelloController {
    private final HelloService helloService;


    @GetMapping("/hello")
    public String hello(String name){
        return helloService.sayHello(name);
    }
    @GetMapping("/hello/{name}")
    public String helloPath(@PathVariable String name){
        return helloService.sayHello(name);
    }
    @PostMapping("/hello/member")
    public String regMember(@ModelAttribute Member member){
        System.out.println("request member = " + member);
        return helloService.saveMember(member);
    }
    @PostMapping("/hello/member2")
    public String regMember2(@RequestBody Member member){
        System.out.println("request member = " + member);
        return helloService.saveMember(member);
    }
    @GetMapping("/hello/member/{id}")
    public Member findMember(@PathVariable Long id){
        return helloService.findById(id);
    }

}