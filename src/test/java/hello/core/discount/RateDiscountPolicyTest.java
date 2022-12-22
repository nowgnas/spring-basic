package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("Vip는 10% 할인이 적용되어야 한다")
    void vip_o() { // 성공 테스트
        // given
        Member member = new Member(1L, "memberVip", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("vip가 아닌 경우 적용되지 않아야 한다")
    void vip_x() { // 실패 테스트 케이스
        // given
        Member member = new Member(2L, "memberVip", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
//        Assertions.assertThat(discount).isEqualTo(10000);
    }
}