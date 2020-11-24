package com.bankwithmint.inventorymanagement.service;

import com.bankwithmint.inventorymanagement.model.Product;
import com.bankwithmint.inventorymanagement.payload.CreateProductRequestDTO;
import com.bankwithmint.inventorymanagement.payload.UpdateProductRequestDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product createProduct(CreateProductRequestDTO requestDTO);
    Product findProductById(UUID id);
    Product updateProduct(UpdateProductRequestDTO requestDTO);
    Product updateProduct(Product product);

    List<Product> getAvailableProducts();
    Product getAvailableProductById(UUID id);
}
