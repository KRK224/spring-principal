package spring.principal.core;

import spring.principal.core.discount.FixDiscountPolicy;
import spring.principal.core.member.MemberServiceImpl;
import spring.principal.core.member.MemoryMemberRepository;
import spring.principal.core.member.interfaces.MemberService;
import spring.principal.core.order.OrderServiceImpl;
import spring.principal.core.order.interfaces.OrderService;

public class AppConfig {
    static public MemberService memberService() {
        // 생성자 주입
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    static public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
