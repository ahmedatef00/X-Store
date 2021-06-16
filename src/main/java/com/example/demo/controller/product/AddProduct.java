package com.example.demo.controller.product;

import com.example.demo.model.Category;
import com.example.demo.model.Image;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ImageService;
import com.example.demo.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@MultipartConfig(maxFileSize = 1024 * 1024 * 4)
@WebServlet(name = "addProduct", urlPatterns = "/addProduct")
public class AddProduct extends HttpServlet {

    private static final long serialVersionUID = 5014950216156492778L;

    CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryService = (CategoryService) getServletContext().getAttribute("categoryService");
        List<Category> allCategories = categoryService.getAllCategories();
        req.setAttribute("allCategories", allCategories);
        req.setAttribute("product", new Product());
        req.getRequestDispatcher("add-product.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoryService = (CategoryService) getServletContext().getAttribute("categoryService");
        String productName = (String) req.getParameter("productName");
        String description = (String) req.getParameter("description");
        String manufacturingName = (String) req.getParameter("manufacturingName");
        String manufacturingDate = (String) req.getParameter("manufacturingDate");
        String expirationDate = (String) req.getParameter("expirationDate");
        String quantity = (String) req.getParameter("quantity");
        String buyPrice = (String) req.getParameter("buyPrice");
        String sellPrice = (String) req.getParameter("sellPrice");
        String categoryId = (String) req.getParameter("categories");
//        Part images = req.getPart("images");
        Product product = new Product();
        Category categoryById = categoryService.getCategoryById(Long.parseLong(categoryId));

        //todo validation
        //@noura
        ImageService imageService = (ImageService) getServletContext().getAttribute("imageService");
        String userHomeDir = System.getProperty("user.home") + "/iti-store/images";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        Set<Image> allImages = imageService.saveImage(userHomeDir, req.getParts());
        product.setProductName(productName);
        product.setDeleted(false);
        product.setDescription(description);
        product.setManufacturingName(manufacturingName);
        product.setManufacturingDate(LocalDate.parse(manufacturingDate, formatter));
        if (expirationDate != null && !expirationDate.trim().isEmpty()) {
            product.setExpirationDate(LocalDate.parse(expirationDate, formatter));
        }
        product.setQuantity(Integer.parseInt(quantity));
        product.setPrimaryImage(allImages.stream().findFirst().get());
        product.setBuyPrice(Double.parseDouble(buyPrice));
        product.setSellPrice(Double.parseDouble(sellPrice));
        product.getImages().addAll(allImages);
        product.getCategories().add(categoryById);


        ProductService productService = (ProductService) getServletContext().getAttribute("productService");
        Product product1 = productService.addNewProduct(product);
        if (product1.getProductId() > 0) {
            resp.sendRedirect("/iti-store/products");
        }
    }
}
