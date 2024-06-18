package spring.principal.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.principal.core.discount.interfaces.DiscountPolicy;
import spring.principal.core.member.interfaces.MemberRepository;

@SpringBootTest
class CoreApplicationTests {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	DiscountPolicy discountPolicy;

	@Test
	void basicBean() {
		System.out.println("이런 식으로 실제 코드와 무관한 spring boot test를 실행하는 경우에 사용한다.");
		System.out.println("memberRepository = " + memberRepository);
		System.out.println("discountPolicy = " + discountPolicy);
	}

	@Test
	void contextLoads() {
	}

}
