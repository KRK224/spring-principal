package spring.principal.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.principal.core.AutoAppConfig;
import spring.principal.core.discount.interfaces.DiscountPolicy;
import spring.principal.core.member.Grade;
import spring.principal.core.member.Member;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member userA = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(userA, 20000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(userA, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

    }

    @RequiredArgsConstructor
    static class DiscountService {
        // 자동 주입
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public int discount(Member member, int price, String discountPolicy) {
            return policyMap.get(discountPolicy).discount(member, price);
        }
    }

}
