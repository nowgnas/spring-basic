package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 " + bean1);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean2 " + bean2);

        Assertions.assertThat(bean1).isSameAs(bean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("singleton init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("singletonBean destroy");
        }
    }
}
