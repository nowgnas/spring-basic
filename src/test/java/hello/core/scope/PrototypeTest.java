package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    void prototypeBeanTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1 ");
        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean 2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("proto1 " + bean);
        System.out.println("proto2 " + bean2);

        Assertions.assertThat(bean).isNotSameAs(bean2);

        ac.close(); // close가 되지 않는다
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("prototype init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototype destroy");
        }
    }
}
