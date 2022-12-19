    package hello.core.member;

    import hello.core.AppConfig;
    import org.assertj.core.api.Assertions;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    public class MemberServiceTest {
        // ---------------- 관심사 분리 ----------------
        MemberService memberService;

        @BeforeEach
        public void beforeEach() {
            AppConfig appConfig = new AppConfig();
            memberService = appConfig.memberService();
        }

            @Test
        void join() {
            // given
            Member member = new Member(1L, "membera", Grade.VIP);

            // when
            memberService.join(member);
            Member findMember = memberService.findMember(1L);

            // then
            Assertions.assertThat(member).isEqualTo(findMember);
        }
        // ---------------- 관심사 분리 ----------------
//        MemberService memberService = new MemberServiceImpl();
//
//        @Test
//        void join(){
//            // given
//            Member member = new Member(1L, "memberA", Grade.VIP);
//            // when
//            memberService.join(member);
//            Member findMember = memberService.findMember(1L);
//            // then
//            Assertions.assertThat(member).isEqualTo(findMember);
//        }
    }
