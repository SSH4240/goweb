package com.kit.dormitory;

import com.kit.dormitory.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentScanTest {
    @Test
    void comScanTest()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

    }
}
