package com.example.demo.utilty;

import com.example.demo.model.Product;
import com.example.demo.model.Purchase;
import com.example.demo.model.dto.ProductDto;
import com.example.demo.model.dto.PurchaseDto;

public class PurchaseMapper {
    public static PurchaseDto mapPurchase(Purchase purchase) {
        ProductDto productDto = ProductMapper.mapToProductDto(purchase.getProduct());
        return new PurchaseDto(purchase.getQuantity(), purchase.getProductBuyPrice(), productDto);
    }

    public static Purchase mapPurchase(PurchaseDto purchaseDto) {
        Product product = ProductMapper.mapToProductDto(purchaseDto.getProduct());
        Purchase purchase = new Purchase(purchaseDto.getQuantity(), purchaseDto.getProductBuyPrice(), product);

        return purchase;
    }
}
