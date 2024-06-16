package spring.principal.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.plaf.nimbus.State;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean = ac.getBean(StatefulService.class);
        StatefulService bean1 = ac.getBean(StatefulService.class);

        bean.order("userA", 10000);
        bean1.order("userB", 20000);

        System.out.println("price = " + bean.getPrice());


    }

    @Configuration
    public static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}