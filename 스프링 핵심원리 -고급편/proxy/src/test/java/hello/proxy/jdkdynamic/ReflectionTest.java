package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    
    @Test
    public void reflection0() throws Exception{
        Hello target = new Hello();

        //공통 로직 1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);
        //공통 로직 1 종료

        //공통 로직 2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result2={}", result2);
        //공통 로직 2 종료
    }

    @Test
    public void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazzHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메소드 정보
        Method methodCallA = clazzHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);//동적으로 변경해야 되는 기능
        log.info("result1={}", result1);

        Method methodCallB = clazzHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    public void reflection2() throws Exception{
        Class<?> clazzHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메소드 정보
        Method methodCallA = clazzHello.getMethod("callA");
        dynamicCall(methodCallA,target);

        Method methodCallB = clazzHello.getMethod("callB");
        dynamicCall(methodCallB,target);
    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }
    @Slf4j
    static class Hello{
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }

}
