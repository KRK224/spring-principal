package spring.principal.core.scan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.context.annotation.ComponentScan.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring.principal.core.scan.filter.MyExcludeComponent;
import spring.principal.core.scan.filter.MyIncludeComponent;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("key = " + beanDefinitionName +", bean = " + bean);
            }
        }

        /**
         * main test code
         */
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));

    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = {MyExcludeComponent.class})
    )
    static class ComponentFilterAppConfig {

    }
}
