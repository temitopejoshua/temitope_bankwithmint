package com.bankwithmint.inventorymanagement.payload;

import com.sun.istack.NotNull;

import java.math.BigDecimal;

public class CreateProductRequestDTO {
    @NotNull
    private String productName;
    @NotNull
    private String productDescription;
    @NotNull
    private BigDecimal productPrice;
    @NotNull
    private Long quantityInStock;
    private Long minimumOrderQuantity;
    @NotNull
    private String productCurrency;
    @NotNull
    private String productImageURL;

    public CreateProductRequestDTO() {
    }

    public CreateProductRequestDTO(String productName, String productDescription, BigDecimal productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public CreateProductRequestDTO(String productName, String productDescription, BigDecimal productPrice, long quantityInStock, long minimumOrderQuantity, String productImageURL) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantityInStock = quantityInStock;
        this.minimumOrderQuantity = minimumOrderQuantity;
        this.productImageURL = productImageURL;

    }

    public Long getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Long getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public void setMinimumOrderQuantity(Long minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    public String getProductCurrency() {
        return productCurrency;
    }

    public void setProductCurrency(String productCurrency) {
        this.productCurrency = productCurrency;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImageURL() {
        return productImageURL;
    }

    public void setProductImageURL(String productImageURL) {
        this.productImageURL = productImageURL;
    }
}
