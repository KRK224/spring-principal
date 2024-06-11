package spring.principal.core;

import spring.principal.core.discount.FixDiscountPolicy;
import spring.principal.core.discount.RateDiscountPolicy;
import spring.principal.core.discount.interfaces.DiscountPolicy;
import spring.principal.core.member.MemberServiceImpl;
import spring.principal.core.member.MemoryMemberRepository;
import spring.principal.core.member.interfaces.MemberService;
import spring.principal.core.order.OrderServiceImpl;
import spring.principal.core.order.interfaces.OrderService;

public class AppConfig {
    public MemberService memberService() {
        // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    // 메소드 명으로 역할을 알 수 있다. 그리고 메소드 내부만 변경하여 중복을 제거
    private static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {

//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
