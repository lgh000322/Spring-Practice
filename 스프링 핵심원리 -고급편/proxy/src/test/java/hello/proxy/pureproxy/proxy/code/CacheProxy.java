package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject {

    //실제 객체
    private Subject target;

    private String cacheData;

    public CacheProxy(Subject target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("프록시 호출");
        if (cacheData == null) {
            cacheData = target.operation();
        }

        return cacheData;
    }
}
