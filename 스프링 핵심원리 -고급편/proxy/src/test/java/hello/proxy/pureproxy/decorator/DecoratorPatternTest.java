package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    public void noDecorator() throws Exception{
        DecoratorPatternClient client = new DecoratorPatternClient(new RealComponent());
        client.execute();
    }

    @Test
    public void decoratorV1() throws Exception{
        DecoratorPatternClient client = new DecoratorPatternClient(new MessageDecorator(new RealComponent()));
        client.execute();
    }

    @Test
    public void decoratorV2() throws Exception{
        DecoratorPatternClient client = new DecoratorPatternClient(new TimeDecorate(new MessageDecorator(new RealComponent())));
        client.execute();
    }
}
