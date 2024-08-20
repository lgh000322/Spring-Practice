package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    FieldService fieldService = new FieldService();

    @Test
    public void field() throws Exception{
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("로직1");
        };

        Runnable userB = () -> {
            fieldService.logic("로직2");
        };

        Thread threadA = new Thread(userA);
        threadA.setDaemon(true);
        threadA.setName("thread-1");

        Thread threadB = new Thread(userB);
        threadA.setDaemon(true);
        threadA.setName("thread-2");

        threadA.start();
//        sleep(2000);
        sleep(100);
        threadB.start();

        sleep(3000);
        log.info("main exit");
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
