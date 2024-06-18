package spring.principal.core.order;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import spring.principal.core.discount.FixDiscountPolicy;
import spring.principal.core.member.Grade;
import spring.principal.core.member.Member;
import spring.principal.core.member.MemoryMemberRepository;

class OrderServiceImplTest {

    @Test
    void createOrder(){

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "memberA", Grade.VIP));

        // 생성자 주입으로 만들면 컴파일 에러가 발생
        // 가장 좋은 에러는 컴파일 에러이다.
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        System.out.println("order = " + order);
    }

}