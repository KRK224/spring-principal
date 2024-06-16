package spring.principal.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.principal.core.member.interfaces.MemberRepository;
import spring.principal.core.member.interfaces.MemberService;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired  // MemberRepository.class에 맞는 빈을 검색해서 넣어준다. e.g. ac.getBean(MemberRepository.class);
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
