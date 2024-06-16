package spring.principal.core;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import spring.principal.core.discount.RateDiscountPolicy;
import spring.principal.core.discount.interfaces.DiscountPolicy;
import spring.principal.core.member.MemberServiceImpl;
import spring.principal.core.member.MemoryMemberRepository;
import spring.principal.core.member.interfaces.MemberRepository;
import spring.principal.core.member.interfaces.MemberService;
import spring.principal.core.order.OrderServiceImpl;
import spring.principal.core.order.interfaces.OrderService;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    // 메소드 명으로 역할을 알 수 있다. 그리고 메소드 내부만 변경하여 중복을 제거
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("AppConfig.discountPolicy");
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
