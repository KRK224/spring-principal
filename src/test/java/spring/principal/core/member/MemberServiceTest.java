package spring.principal.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.principal.core.AppConfig;
import spring.principal.core.member.interfaces.MemberService;

public class MemberServiceTest {
    MemberService memberService;
    @BeforeEach
    public void createMemberService() {
        AppConfig appConfig = new AppConfig();
        this.memberService = appConfig.memberService();
    }
    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
