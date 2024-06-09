package spring.principal.core.order;

import spring.principal.core.discount.FixDiscountPolicy;
import spring.principal.core.discount.interfaces.DiscountPolicy;
import spring.principal.core.member.Member;
import spring.principal.core.member.MemoryMemberRepository;
import spring.principal.core.member.interfaces.MemberRepository;
import spring.principal.core.order.interfaces.OrderService;

public class OrderServiceImpl implements OrderService {
    
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // Order 서비스에서는 discountPolicy의 할인 구현체는 알 수 없다.(의존성이 없다 = 의존역전(DI))
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
