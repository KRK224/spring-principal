package spring.principal.core;

import spring.principal.core.discount.FixDiscountPolicy;
import spring.principal.core.member.Grade;
import spring.principal.core.member.Member;
import spring.principal.core.member.MemberServiceImpl;
import spring.principal.core.member.MemoryMemberRepository;
import spring.principal.core.member.interfaces.MemberService;
import spring.principal.core.order.Order;
import spring.principal.core.order.OrderServiceImpl;
import spring.principal.core.order.interfaces.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = AppConfig.memberService();
        OrderService orderService = AppConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
