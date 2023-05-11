package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.persistence.MemberRepository;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class TestStudy {
//    @Test
//    @DisplayName("객체 생성")
//    void create(){
//        Member member = new Member("kim", 20);
//        Assertions.assertNotNull(member);
//    }
//
//    @Test
//    void test1(){
//        System.out.println("test1");
//    }
//
//    @Test
//    void test2(){
//        System.out.println("test2");
//    }
//    @BeforeAll
//    static void beforeAll(){
//        System.out.println("before all");
//    }
//
//    @AfterAll
//    static void afterAll(){
//        System.out.println("After All");
//    }
//
//    @BeforeEach
//    void beforeEach(){
//        System.out.println("Before Each");
//    }
//
//    @AfterEach
//    void afterEach(){
//        System.out.println("After Each");
//    }

    @Test
    void simpleTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComAppConfig.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Member member = new Member("kim", 20);
        memberRepository.save(member);
        Member findMember = memberRepository.findById(1L);
        System.out.println("member = " + member);
        System.out.println("findMember = " + findMember);
        Assertions.assertEquals(member ,findMember, ()->member+"와"+findMember+"는 같아야 함");
    }
    @Test
    void ThrowAssert테스트(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Member("kim", 10));
        String message = exception.getMessage();
        System.out.println("message = " + message);
    }

    @Test
    void assert_Timeout테스트(){
        Assertions.assertTimeout(Duration.ofMillis(100), ()->{
            new Member("kim", 30);
            Thread.sleep(101);
        }, ()->"Member 생성은 100ms 안에 끝나야 함");
    }
    @Test
    void 같은지테스트(){
        Member member1 = new Member("kim", 40);
        Member member2 = new Member("kim", 30);
        assertThat(member2).withFailMessage("저장한 멤버와 조회한 멤버는 같아야 한단다").isEqualTo(member1);
    }
}
