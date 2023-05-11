package com.kit.dormitory.member;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberStorage memberStorage;

    @Autowired(required = false)
    public MemberServiceImpl(MemberStorage memberStorage) {
        this.memberStorage = memberStorage;
    }

    @Override
    public void register(Member member) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        memberStorage.store(member);
        stopWatch.stop();
        System.out.println("stopWatch getTotalTimeMiles() = " + stopWatch.getTotalTimeMillis());
        memberStorage.store(member);

    }
    @Override
    public Member findMember(Member member) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Member findMember = memberStorage.findById(member.getId());
        stopWatch.stop();
        System.out.println("stopWatch getTotalTimeMiles() = " + stopWatch.getTotalTimeMillis());
        return findMember;
    }
}
