package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 dI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조 값이 다른 것을 확인
        System.out.println("member service 1 " + memberService1);
        System.out.println("member service 2 " + memberService2);

        // 객체가 다르게 생성되는 것을 볼 수 있다
        Assertions.assertThat(memberService2).isNotSameAs(memberService1);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonService() {
        // 같은 객체를 가지고 온다
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println(singletonService1);
        System.out.println(singletonService2);

        // isSameAs == 인스턴스 비교
        // isEquals
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();
        // spring container가 싱글톤으로 만들어 주기 때문에 싱글톤을 직접 구현할 필요가 없다
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);


        // 참조 값이 다른 것을 확인
        System.out.println("member service 1 " + memberService1);
        System.out.println("member service 2 " + memberService2);

        // 객체가 다르게 생성되는 것을 볼 수 있다
        Assertions.assertThat(memberService2).isSameAs(memberService1);

    }
}
