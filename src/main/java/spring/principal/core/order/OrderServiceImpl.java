package spring.principal.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import spring.principal.core.discount.interfaces.DiscountPolicy;
import spring.principal.core.member.Member;
import spring.principal.core.member.interfaces.MemberRepository;
import spring.principal.core.order.interfaces.OrderService;

@Component
public class OrderServiceImpl implements OrderService {
    /**
     * MemberRepository가 추상 인터페이스 뿐만 아니라 구체 클래스에도 의존한다.
     * => 구체 클래스를 지워서 DIP를 지킨다? => null point exception 발생.
     */
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // Order 서비스에서는 discountPolicy의 할인 구현체는 알 수 없다.(의존성이 없다 = 의존역전(DI))
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    @Override
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
