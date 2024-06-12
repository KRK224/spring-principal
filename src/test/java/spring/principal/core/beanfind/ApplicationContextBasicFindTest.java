package spring.principal.core.beanfind;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.principal.core.AppConfig;
import spring.principal.core.member.MemberServiceImpl;
import spring.principal.core.member.interfaces.MemberService;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }

    @Test
    @DisplayName("구체 Type으로 조회")
    void findBeanByImplType() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // 가능하나, 좋은 테스트는 아니다.
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /**
     * 언제나 실패 케이스도 테스트 케이스에 추가할 것.
     */

    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByNameFailure() {
        // 2 Argument 로직 실행시, 1 Argument의 에러가 발생해야한다.
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }
}
