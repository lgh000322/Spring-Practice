package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {
    ThreadLocalLogTrace trace;

    @BeforeEach
    void init() {
        trace = new ThreadLocalLogTrace();
    }

    @Test
    public void begin_end_level2() throws Exception{
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }
    @Test
    public void begin_exception_level2() throws Exception{
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2,new IllegalStateException("예외 발생!"));
        trace.exception(status1,new IllegalStateException("예외 발생!"));
    }
}