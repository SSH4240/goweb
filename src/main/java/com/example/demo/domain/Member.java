package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Member {
    private Long id;
    private String name;
    private Integer age;

    public Member(String name, Integer age) {
        if (age>=100||age<=20){
            throw new IllegalArgumentException("나이는 20보다 크고 100보다 작아야 함");
        }
        this.name = name;
        this.age = age;
    }

    public Member(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
