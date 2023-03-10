package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository; // 추상화에만 의존
    @Autowired // 자동 의존 관계 주입
    // MemberRepository에 맞는 것을 자동으로 주입해 준다
    public MemberServiceImpl(MemberRepository memberRepository) {
        /*
         * 어떤 구현체가 들어올지 알 수 없다
         * 오직 AppConfig를 통해서만 알 수 있다
         * 의존 관계에 대한 고민은 외부에 맡기고 인터페이스에 의존해 실행에만 집중한다
         *
         * MemberServiceImpl은 MemberRepository인 추상에만 의존하게 된다 이제 DIP를 만족하게 된다
         */
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
