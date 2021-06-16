package com.example.demo.controller.user;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "make-as-admin", urlPatterns = {"/make-as-admin"})
public class MakeAsAdminController extends HttpServlet {

    private static final long serialVersionUID = -9136948782924948631L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = (UserService) getServletContext().getAttribute("userService");
        String idParameter = req.getParameter("id");
        String adminParameter = req.getParameter("admin");
        if (idParameter != null && adminParameter != null) {

            Long id = Long.parseLong(idParameter);
            Role role = Role.CUSTOMER_ROLE;
            try {
                UserDto user = userService.findUserById(id);
                if (adminParameter.equals("true")) {
                    role = Role.ADMIN_ROLE;
                }
                userService.updateUserRole(user.getEmail(), role);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("customers");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
