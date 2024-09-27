package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint proceedingJoinPoint, Retry retry) throws Throwable {
        log.info("[retry] {}, retry={}", proceedingJoinPoint.getSignature(), retry);

        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
                return proceedingJoinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }

        throw exceptionHolder;
    }
}
