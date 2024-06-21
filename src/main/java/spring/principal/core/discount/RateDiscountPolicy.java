package spring.principal.core.discount;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import spring.principal.core.annotation.MainDiscountPolicy;
import spring.principal.core.discount.interfaces.DiscountPolicy;
import spring.principal.core.member.Grade;
import spring.principal.core.member.Member;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade()==Grade.VIP) {
            return price * discountPercent / 100;
        } else
            return 0;
    }
}
