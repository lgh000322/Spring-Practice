package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * 1.파라미터 전송기능
 *
 */
@WebServlet(name = "requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회-start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + ": " + request.getParameter(paramName)));
        System.out.println("전체 파라미터 조회-end");

        System.out.println();

        System.out.println("단일 파라미터 조회-start");
        String userName = request.getParameter("userName");
        String age = request.getParameter("age");

        System.out.println("userName = " + userName);
        System.out.println("age = " + age);
        System.out.println("단일 파라미터 조회-end");

        System.out.println();
        System.out.println("이름이 같은 복수파라미터 조회");
        String[] userNames = request.getParameterValues("userName");
        for (String name : userNames) {
            System.out.println("userName = " + name);
        }

        response.getWriter().write("ok");
    }
}
