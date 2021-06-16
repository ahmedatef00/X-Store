package com.example.demo.controller.product;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteProduct", urlPatterns = {"/deleteProduct"})
public class DeleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductService productService = (ProductService) getServletContext().getAttribute("productService");
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Product product = productService.findById(Long.parseLong(idParam));
            productService.remveProduct(product);
        }
        resp.sendRedirect("/iti-store/products");
    }
}
