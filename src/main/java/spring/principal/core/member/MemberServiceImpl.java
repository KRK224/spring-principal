package spring.principal.core.member;

import spring.principal.core.member.interfaces.MemberRepository;
import spring.principal.core.member.interfaces.MemberService;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

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
