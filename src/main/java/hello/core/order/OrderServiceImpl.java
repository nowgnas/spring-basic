package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    /*
     * member 와 할인에 대한 구현체를 가져와서 사용하고 있다
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    /*
     * DiscountPolicy와 MemberRepository는 철저히 인터페이스에만 의존하고 있다
     */
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;
    /*
     * OrderServiceImpl에 DiscountPolicy를 주입해 줘야 한다
     */

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 멤버 찾기
        /*
         * 단일 책임 원칙 (SRP) 이 잘 지켜진 것
         * order에서는 discount policy에 대해 신경을 쓰지 않는다
         * discount policy가 알아서 해준다
         * 결과만 던져 주면 된다
         */
        int discountPrice = discountPolicy.discount(member, itemPrice); // 정책에 데이터를 넘긴다

        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문이 생성 된다
    }
}
