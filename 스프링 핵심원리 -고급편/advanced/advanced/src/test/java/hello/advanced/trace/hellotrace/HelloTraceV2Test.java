package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV2Test {
    HelloTraceV2 trace;
    @BeforeEach
    void init() {
        trace = new HelloTraceV2();
    }
    @Test
    public void begin_end() throws Exception{
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @Test
    public void begin_exception() throws Exception{
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}