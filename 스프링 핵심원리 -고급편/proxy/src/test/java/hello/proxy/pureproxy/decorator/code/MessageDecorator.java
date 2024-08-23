package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator extends Decorator{


    public MessageDecorator(Component component) {
        super(component);
    }

    @Override
    public String operation() {
        log.info("메시지 데코레이터 실행");
        String result = component.operation();
        String decorate = "******" + result + "******";
        log.info("메시지 데코레이터 적용 전={}, 적용 후={}",result,decorate);
        return decorate;
    }
}
