package spring.principal.core;

import spring.principal.core.member.Grade;
import spring.principal.core.member.Member;
import spring.principal.core.member.interfaces.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = AppConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());



    }
}
