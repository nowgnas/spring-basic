package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {


    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//
//        MemberService memberService = appConfig.memberService(); // 인터페이스를 준다
////        MemberService memberService = new MemberServiceImpl();

        /*
         * AppConfig에 있는 Bean을 Spring 컨테이너에 등록해준다
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "membera", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member: " + member.getName());
        System.out.println("find member: " + findMember.getName());
    }
}
