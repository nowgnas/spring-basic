package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

//    @Test
//    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상이면 중복 오류 발생")
//    void findBeanByTypeDuplicate() {
//        MemberRepository bean = ac.getBean(MemberRepository.class); // 타입만 지정
//        assertThrows(NoUniqueBeanDefinitionException.class, () ->
//                ac.getBean(MemberRepository.class));
//    }

//    @Test
//    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
//    void findBeanByName() {
//        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
//        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
//    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByName() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key :
                beansOfType.keySet()) {
            System.out.println("key: " + key);
        }

    }

    @Configuration
    static class SameBeanConfig {
        // scope를 이 클래스 안에서만 사용하겠다
        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }


    }

}
