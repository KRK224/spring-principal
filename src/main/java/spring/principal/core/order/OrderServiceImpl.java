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
    
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    /**
     * 수정자 주입 테스트: 수정자 주입 시에 Component 등록과 의존 관계 주입이 2단계로 이루어진다.
     */
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }

    /**
     * 생성자 주입 시에는 Component 빈 등록과 의존 관계 주입이 동시에 이루어진다.
     * new OrderServiceImpl(ac.getBean(MemberRepository.class), ac.getBean(DiscountPolicy.class));
     * @param memberRepository
     * @param discountPolicy
     */
    // 생성자가 하나인 경우, Autowired를 생략 가능하다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {

        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
