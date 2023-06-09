package online.shop.service;

import lombok.RequiredArgsConstructor;
import online.shop.domain.Member;
import online.shop.repository.MemberRepository;
import online.shop.repository.SpringDataMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final SpringDataMemberRepository memberRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).get();
    }
}
