package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // 관심사 분리를 위한 AppConfig

    @Bean
    public MemberService memberService() {
        // 어떤 구현체가 들어갈지 정해준다
        // 생성자를 통해 주입 된다
        // MemoryMemberRepository 생성자를 주입해 준다고 표현한다 injection
        return new MemberServiceImpl(memberRepository()); // 역할을 정의
    }

    @Bean
    public MemberRepository memberRepository() { // 구현체를 정의
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() { // 역할
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() { // 구현체
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // 새로운 정률 할인 정책으로 수정하기
    }

}
