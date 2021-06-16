package com.example.demo.controller.product;

import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.service.ProductService;
import com.example.demo.utilty.ProductMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "view-product", urlPatterns = {"/view-product"})
public class ViewProductController extends HttpServlet {

    private static final long serialVersionUID = -5076520322013207207L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            ProductService productService = (ProductService) getServletContext().getAttribute("productService");
            try {
                Product product = productService.findById(Long.parseLong(id));

                ProductDto productDto = ProductMapper.mapToProductDto(product);
                req.setAttribute("product", productDto);
                req.getRequestDispatcher("view-product.jsp").include(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("/iti-store/error");
            }

        } else {
            resp.sendRedirect("/iti-store/products");
        }

    }
}
