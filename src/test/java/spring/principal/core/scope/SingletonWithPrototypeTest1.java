package spring.principal.core.scope;


import static org.assertj.core.api.Assertions.assertThat;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        prototypeBean1.addCount();
        System.out.println("prototypeBean1.getCount() = " + prototypeBean1.getCount());
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        prototypeBean2.addCount();
        System.out.println("prototypeBean2.getCount() = " + prototypeBean2.getCount());
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }



    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count = clientBean1.logic();
        assertThat(count).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
        // 만약 logic안에서 prototypeBean을 받아서 호출하면 항상 1이다...
//        int count3 = clientBean2.logic();
//        assertThat(count3).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {
//        public PrototypeBean prototypeBean;
        public final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        @Autowired
        public ClientBean(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

        @PostConstruct
        public void init() {
            System.out.println("ClientBean.init");
        }

        @PreDestroy
        public void close() {
            System.out.println("ClientBean.close");
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init = " + this);

        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
