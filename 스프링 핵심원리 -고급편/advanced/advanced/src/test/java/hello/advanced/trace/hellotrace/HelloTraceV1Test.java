package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class HelloTraceV1Test {
    HelloTraceV1 trace;
    @BeforeEach
    void init() {
       trace = new HelloTraceV1();
    }
    @Test
    public void begin_end() throws Exception{
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    public void begin_exception() throws Exception{
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }

}