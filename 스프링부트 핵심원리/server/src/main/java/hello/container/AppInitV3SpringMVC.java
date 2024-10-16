package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV3SpringMVC implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("AppInitV3SpringMVC.onStartup");

        //스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(HelloConfig.class);

        //스프링 MVC 디스패처 서블릿
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        //디스패처 서블릿을 서블릿 컨테이너에 등록
        servletContext.addServlet("dispatcherV3", dispatcherServlet)
                .addMapping("/");
    }
}
