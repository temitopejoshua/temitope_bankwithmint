package com.bankwithmint.inventorymanagement.serviceimpl;

import com.bankwithmint.inventorymanagement.exception.ProductNotAvailableException;
import com.bankwithmint.inventorymanagement.model.Product;
import com.bankwithmint.inventorymanagement.payload.CreateProductRequestDTO;
import com.bankwithmint.inventorymanagement.payload.UpdateProductRequestDTO;
import com.bankwithmint.inventorymanagement.repository.ProductRepository;
import com.bankwithmint.inventorymanagement.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Product createProduct(CreateProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setImageURL(requestDTO.getProductImageURL());
        product.setMinimumOrderQuantity(requestDTO.getMinimumOrderQuantity());
        product.setProductCurrency(requestDTO.getProductCurrency());
        product.setProductDescription(requestDTO.getProductDescription());
        product.setQuantityInStock(requestDTO.getQuantityInStock());
        product.setProductName(requestDTO.getProductName());
        product.setProductUnitPrice(requestDTO.getProductPrice());
        productRepository.save(product);

        LOGGER.info("-------Created product {} ", product);
        return product;
    }

    @Override
    public Product findProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No product found with the specified id " + id));

    }

    @Override
    public Product updateProduct(UpdateProductRequestDTO requestDTO) {
        Product product = findProductById(requestDTO.getProductId());
        if(requestDTO.getProductCurrency() !=null)
            product.setProductCurrency(requestDTO.getProductCurrency());
        if(requestDTO.getProductDescription() !=null)
            product.setProductDescription(requestDTO.getProductDescription());
        if(requestDTO.getProductImageURL() !=null)
            product.setImageURL(requestDTO.getProductImageURL());
        if(requestDTO.getQuantityInStock() != null)
            product.setQuantityInStock(requestDTO.getQuantityInStock());
        if(requestDTO.getProductPrice() !=null)
            product.setProductUnitPrice(requestDTO.getProductPrice());
        if(requestDTO.getProductName() !=null)
            product.setProductName(requestDTO.getProductName());
        if(requestDTO.getMinimumOrderQuantity() !=null)
            product.setMinimumOrderQuantity(requestDTO.getMinimumOrderQuantity());

        LOGGER.info("-------Updating product {} ", product);

        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> getAvailableProducts() {
        LOGGER.info("-------- Fetching all available products -------");

        return productRepository.findAllAvailableproducts();
    }

    @Override
    public Product getAvailableProductById(UUID id) {
        Product product = findProductById(id);
        if (product.getQuantityInStock() <= 0)
            throw new ProductNotAvailableException(HttpStatus.BAD_REQUEST, "Product out of stock " + id);
        return product;
    }
}
