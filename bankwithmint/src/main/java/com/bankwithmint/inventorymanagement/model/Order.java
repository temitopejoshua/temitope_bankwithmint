package com.bankwithmint.inventorymanagement.model;

import com.bankwithmint.inventorymanagement.constants.OrderStatus;
import com.bankwithmint.inventorymanagement.utility.EncryptionUtil;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID orderId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "price_on_order_creation", columnDefinition = "text")
    private BigDecimal priceOnOrderCreation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Transient
    private BigDecimal costOfItems;


    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getPriceOnOrderCreation() {
        return EncryptionUtil.decryptBigDecimal(priceOnOrderCreation);
    }

    public void setPriceOnOrderCreation(BigDecimal priceOnOrderCreation) {
        priceOnOrderCreation = EncryptionUtil.encryptBigDecimal(priceOnOrderCreation);
        this.priceOnOrderCreation = priceOnOrderCreation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public BigDecimal getCostOfItems() {
        return getPriceOnOrderCreation().multiply(BigDecimal.valueOf(getQuantity()));
    }

    public void setCostOfItems(BigDecimal costOfItems) {

        this.costOfItems = getPriceOnOrderCreation().multiply(BigDecimal.valueOf(getQuantity()));
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + orderId +
                ", product=" + product +
                ", quantity=" + quantity +
                ", orderStatus=" + orderStatus +
                ", priceOnOrderCreation=" + priceOnOrderCreation +
                ", customer=" + customer +
                ", createdDate=" + createdDate +
                ", updatedTime=" + updatedTime +
                ", costOfItems=" + costOfItems +
                '}';
    }
}
