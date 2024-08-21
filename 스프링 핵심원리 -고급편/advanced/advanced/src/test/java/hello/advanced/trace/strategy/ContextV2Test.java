package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    public void strategyV1() throws Exception{
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직 1 수행"));
        context.execute(() -> log.info("비즈니스 로직 2 수행"));
    }
}
