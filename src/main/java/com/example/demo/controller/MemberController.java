package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.persistence.MemberRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/new")
    public String newMember(){
        return "new-form";
    }

    @PostMapping(value = "/add")
    public String addMember(@ModelAttribute Member member, Model model)
    {
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "add-result";
    }
    @RequestMapping("/all")
    public ModelAndView allMember()
    {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("member-list");
        mv.addObject("members", members);
        return mv;
    }
    @GetMapping("/api/{id}")
    @ResponseBody
    public String getMemberById(@PathVariable("id") Long id){
        return memberRepository.findById(id).getName();
    }
    @PostMapping("/requestBody")
    @ResponseBody
    public String requestBodyHandler(@RequestBody Member member){
        log.info("member={}", member);
        return "ok";
    }
    @PostMapping("/responseBody2")
    @ResponseBody
    public Member requestBodyHandler2(@RequestBody Member member){
        log.info("name={}", member.getName());
        log.info("name={}", member.getAge());
        return member;
    }
}
