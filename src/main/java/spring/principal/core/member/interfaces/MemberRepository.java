package spring.principal.core.member.interfaces;

import spring.principal.core.member.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
