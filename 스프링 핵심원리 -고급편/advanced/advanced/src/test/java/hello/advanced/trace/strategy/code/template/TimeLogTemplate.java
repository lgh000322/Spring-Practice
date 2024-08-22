package hello.advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(CallBack callBack) {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행(핵심 기능)
        callBack.call();//상속
        //비즈니스 로직 종료(핵심 기능 종료)
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);    }
}
