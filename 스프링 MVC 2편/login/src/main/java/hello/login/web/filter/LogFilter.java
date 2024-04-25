package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("로그 필터 초기화");
    }

    @Override
    public void destroy() {
        log.info("로그 필터 destroy");
    }

    //http 요청이 올때마다 dofilter를 호출함
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter do filter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("Request [{}][{}]", uuid, requestURI);
            chain.doFilter(request,response);
        } catch (Exception e) {
            throw e;
        } finally{
            log.info("Response[{}][{}]", uuid, requestURI);
        }
    }
}
