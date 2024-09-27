package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    /**
     * 생성자 주입을 할 경우 순환참조가 발생하며 실행이 되지않는다.
     * 스프링은 생성자 주입과 세터주입이 분리되어 있고, 생성자 주입후 세터 주입을 사용함으로
     * 세터 주입을 하면 생성자 주입때 발생한 순환참조 문제를 해결할 수 있다.
     * 스프링 부트 2.6버전 이상부턴 다음 설정을 추가해줘야 한다.
     * spring.main.allow-circular-references=true
     * 또는 @Lazy 어노테이션을 사용한다.
     *
     * @param callServiceV1 프록시 인스턴스
     */
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callService V1 setter={}",callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        //프록시 메소드 호출
        callServiceV1.internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
