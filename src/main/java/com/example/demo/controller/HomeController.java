package com.example.demo.controller;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HomeController extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
        System.out.println("Init");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html");

        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");
//        RequestDispatcher dispatcher =
        request.getRequestDispatcher("/hello").include(request, response);

        //

    }

    public void destroy() {
        //close resources
        System.out.println("destroy");
    }
}