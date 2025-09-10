package nkm.study.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="requestParamServlet", urlPatterns = "/rquest-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameterNames().asIterator().forEachRemaining(System.out::println);

        String name = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);

        String[] usernames = request.getParameterValues("username");
        for(String username: usernames){
            System.out.println("username = " + username);
        }
        response.getWriter().write("ok");
    }
}
