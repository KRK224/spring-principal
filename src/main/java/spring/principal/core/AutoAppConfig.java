package spring.principal.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.principal.core.discount.interfaces.DiscountPolicy;
import spring.principal.core.member.interfaces.MemberRepository;
import spring.principal.core.order.OrderServiceImpl;
import spring.principal.core.order.interfaces.OrderService;

@Configuration
@ComponentScan(
//        basePackages = "spring.principal.core",
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    DiscountPolicy discountPolicy;

//    @Bean
//    public OrderService orderService() {
//        System.out.println("또한 설정 파일에서도 의존 관계를 주입할 때 사용할 수 있으나... 다른 방법도 많다.");
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }

    // 이런 식으로 Bean 등록 메소드에 파라미터로 지정하면 자동 주입을 해준다.
    @Bean("manualOrderService")
    public OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        return new OrderServiceImpl(memberRepository, discountPolicy);
    }
}
