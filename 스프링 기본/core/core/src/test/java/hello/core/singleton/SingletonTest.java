package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void 순수한_DI_컨테이너() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();

        MemberService memberService1 = appConfig.memberService();

        System.out.println("memberService = " + memberService);

        System.out.println("memberService1 = " + memberService1);

        Assertions.assertThat(memberService).isNotSameAs(memberService1);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체사용")
    void 싱글톤_객체_사용() {
        SingletonService singletonInstance1 = SingletonService.getInstance();

        SingletonService singletonInstance2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonInstance1);
        System.out.println("singletonInstance2 = " + singletonInstance2);

        Assertions.assertThat(singletonInstance1).isSameAs(singletonInstance2);
    }

    @Test
    @DisplayName("싱글톤 컨테이너 테스트")
    void 싱글톤_컨테이너_테스트() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
