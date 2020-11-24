package com.bankwithmint.inventorymanagement.model;


import com.bankwithmint.inventorymanagement.utility.EncryptionUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String productName;
    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String productDescription;
    @Column(name = "unit_price", nullable = false, columnDefinition = "text")
    private BigDecimal productUnitPrice;

    @Column(name = "currency")
    private String productCurrency;
    @Column(name = "quantity_in_stock" , nullable = false)
    private Long quantityInStock;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Column(name = "min_order_quantity")
    private Long minimumOrderQuantity;

    @Column(name = "image_url", nullable = false)
    private String imageURL;

    public Product() {
    }

    public Product(String productName, String productDescription, BigDecimal productUnitPrice, String productCurrency, Long quantityInStock, Long minimumOrderQuantity, String imageURL) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productUnitPrice = productUnitPrice;
        this.productCurrency = productCurrency;
        this.quantityInStock = quantityInStock;
        this.minimumOrderQuantity = minimumOrderQuantity;
        this.imageURL = imageURL;
    }

    public Product(String productName, String productDescription, BigDecimal productUnitPrice, String productCurrency, Long quantityInStock, String imageURL) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productUnitPrice = productUnitPrice;
        this.productCurrency = productCurrency;
        this.quantityInStock = quantityInStock;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    public void setMinimumOrderQuantity(Long minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public BigDecimal getProductUnitPrice() {
        return EncryptionUtil.decryptBigDecimal(productUnitPrice);
    }

    public void setProductUnitPrice(BigDecimal productUnitPrice) {
        productUnitPrice = EncryptionUtil.encryptBigDecimal(productUnitPrice);
        this.productUnitPrice = productUnitPrice;
    }

    public String getProductCurrency() {
        return productCurrency;
    }

    public void setProductCurrency(String productCurrency) {
        this.productCurrency = productCurrency;
    }

    public Long getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="E, MMM dd yyyy HH:mm:ss")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="E, MMM dd yyyy HH:mm:ss")
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productUnitPrice +
                ", productCurrency='" + productCurrency + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", createdDate=" + createdDate +
                ", updatedTime=" + updatedTime +
                ", minimumOrderQuantity=" + minimumOrderQuantity +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
