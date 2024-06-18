package spring.principal.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.principal.core.AutoAppConfig;
import spring.principal.core.member.interfaces.MemberRepository;
import spring.principal.core.member.interfaces.MemberService;
import spring.principal.core.order.interfaces.OrderService;

public class AutoAppConfigTest {
    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderService orderService = ac.getBean(OrderService.class);
        assertThat(orderService.getMemberRepository()).isSameAs(memberService.getMemberRepository());

    }
}
