package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorate extends Decorator {

    public TimeDecorate(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("타임 데코레이터 실행");
        long startTime = System.currentTimeMillis();

        String result = component.operation();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("타임 데코레이터 종료 resultTime ={}MS", resultTime);

        return result;
    }

}
