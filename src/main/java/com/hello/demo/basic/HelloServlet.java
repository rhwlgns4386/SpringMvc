package com.hello.demo.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "helloServlet",urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello");
        System.out.println("username="+req.getParameter("username"));

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        Writer writer=resp.getWriter();
        writer.write(req.getParameter("username"));
    }
}
