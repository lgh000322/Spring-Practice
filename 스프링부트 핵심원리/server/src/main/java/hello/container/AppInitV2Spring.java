package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV2Spring implements AppInit {

    @Override
    public void onStartUp(ServletContext servletContext) {
        System.out.println("AppInitV2Spring.onStartUp");

        //스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(HelloConfig.class);

        //스프링 MVC 디스패처 서블릿
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        //디스패처 서블릿을 서블릿 컨테이너에 등록
        servletContext.addServlet("dispatcherV2", dispatcherServlet)
                .addMapping("/spring/*");
    }
}
