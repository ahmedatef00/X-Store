package com.example.demo.controller.user;

import com.google.gson.Gson;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userInfo", urlPatterns = "/userInfo")
public class UserInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            try {
                UserService userService = (UserService) getServletContext().getAttribute("userService");
                UserDto userDto = userService.findUserById(Long.parseLong(id));
                String json = new Gson().toJson(userDto);
                resp.getWriter().write(json);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                resp.getWriter().write("{}");
            }
        }
    }
}
