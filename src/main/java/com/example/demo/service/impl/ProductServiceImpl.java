package com.example.demo.service.impl;

import com.example.demo.exception.ProductQuantityLimitExceeded;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.dto.ProductSearchExampleDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.impl.ProductRepositoryImpl;
import com.example.demo.service.ProductService;
import com.example.demo.utilty.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class ProductServiceImpl implements ProductService {

    private static ProductServiceImpl instance;
    private ProductRepository productRepository;

    protected ProductServiceImpl() {
        productRepository = ProductRepositoryImpl.getInstance();
    }

    public static synchronized ProductServiceImpl getInstance() {
        return Objects.requireNonNullElseGet(instance,
                () -> {
                    instance = new ProductServiceImpl();
                    return instance;
                });

    }

    @Override
    public Product updateProductQuantity(Product product, int quantity) throws ProductQuantityLimitExceeded {
        int newQuantity = product.getQuantity() + quantity;
        if (newQuantity < 0) {
            throw new ProductQuantityLimitExceeded();
        }
        product.setQuantity(newQuantity);
        return productRepository.save(product);
    }

    @Override
    public List<Product> searchByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> searchByProductDTO(ProductSearchExampleDTO exampleDTO) {
        return productRepository.findByCategoryAndMinMaxPriceAndProductName(exampleDTO);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId);

    }

    @Override
    public List<ProductDto> getAllProducts(List<ProductDto> productDtos) {
        Logger logger
                = Logger.getLogger(ProductServiceImpl.class.getName());
        List<ProductDto> productDtoSet = new ArrayList<>();
        logger.info("in the getAllProudects method");
        for (ProductDto productDto : productDtos) {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            logger.info(productDto.getProductId() + "");
            Product byId = productRepository.findById(productDto.getProductId());
            ProductDto mapedProductDto = ProductMapper.mapToProductDto(byId);
            mapedProductDto.setQuantity(productDto.getQuantity());
            logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<BID>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + byId);
            logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<mapper>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + mapedProductDto);

            productDtoSet.add(mapedProductDto);
        }
        return productDtoSet;
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.update(product);
    }

    @Override
    public void remveProduct(Product product) {
        productRepository.delete(product);
    }
}
