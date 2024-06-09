package spring.principal.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.principal.core.member.Grade;
import spring.principal.core.member.Member;
import spring.principal.core.member.MemberServiceImpl;
import spring.principal.core.member.interfaces.MemberService;
import spring.principal.core.order.interfaces.OrderService;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
    }




}
