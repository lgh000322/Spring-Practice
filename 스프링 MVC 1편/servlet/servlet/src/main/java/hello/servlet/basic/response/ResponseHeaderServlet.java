package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHeaderServlet",urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //http 응답코드[status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        //[response header]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        //[Header] 편의 메소드
      //  content(response);
        // 쿠키 편의 메소드
      //  cookie(response);
        //리다이렉트 메소드
        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.write("ok");

    }

    //header 편의 메소드
    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setContentLength(2);
    }

    //쿠키 편의 메소드
    private void cookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //response.setStatus(HttpServletResponse.SC_FOUND);
        //response.setHeader("Location","/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");

    }
}
