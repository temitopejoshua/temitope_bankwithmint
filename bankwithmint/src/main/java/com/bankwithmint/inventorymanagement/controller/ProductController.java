package com.bankwithmint.inventorymanagement.controller;

import com.bankwithmint.inventorymanagement.payload.CreateProductRequestDTO;
import com.bankwithmint.inventorymanagement.payload.UpdateProductRequestDTO;
import com.bankwithmint.inventorymanagement.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value="/")
    public ResponseEntity<Object> createProduct(@Validated @RequestBody CreateProductRequestDTO createProductRequestDTO){

        return new ResponseEntity<>(productService.createProduct(createProductRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findProductById(@PathVariable("id") UUID productId){

        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }

    @GetMapping(value="/available-products")
    public  ResponseEntity<Object> findAllAvailableProducts(){
        return new ResponseEntity<>(productService.getAvailableProducts(),HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Object> updateProduct(@Validated @RequestBody UpdateProductRequestDTO requestDTO){

        return new ResponseEntity<>(productService.updateProduct(requestDTO), HttpStatus.OK);
    }
}
