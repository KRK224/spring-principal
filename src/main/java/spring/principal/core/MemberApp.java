package spring.principal.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.principal.core.member.Grade;
import spring.principal.core.member.Member;
import spring.principal.core.member.interfaces.MemberService;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        /**
         * AppConfig.class의 설정을 보고 Bean이 붙은 class를 DI 컨테이너에 보관한다.
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());



    }
}
