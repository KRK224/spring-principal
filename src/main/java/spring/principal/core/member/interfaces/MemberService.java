package spring.principal.core.member.interfaces;

import spring.principal.core.member.Member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
