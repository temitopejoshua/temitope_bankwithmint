package com.ibankwithmint.reportapplication.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ibankwithmint.reportapplication.constants.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="order_report")
public class OrderReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "quantity")
    private Long quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    @Column(name = "unit_cost", columnDefinition = "text")
    private BigDecimal unitPrice;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_address")
    private String customerAddress;
    @Column(name = "customer_phone_number")
    private String customerPhoneNumber;
    @Column(name = "total_cost_of_items", columnDefinition = "text")
    private BigDecimal totalCostOfItems;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "order_creation_date")
    private LocalDateTime orderCreationDate;
    @Column(name = "order_id")
    private UUID orderId;

    public OrderReport(Long quantity, OrderStatus orderStatus, BigDecimal unitPrice, LocalDateTime createdDate, String customerName, String customerAddress, String customerPhoneNumber, BigDecimal totalCostOfItems, String productName, LocalDateTime orderCreationDate) {
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.unitPrice = unitPrice;
        this.createdDate = createdDate;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.totalCostOfItems = totalCostOfItems;
        this.productName = productName;
        this.orderCreationDate = orderCreationDate;
    }

    public OrderReport() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getUnitPrice() {
        return  unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="E, MMM dd yyyy HH:mm:ss")
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public BigDecimal getTotalCostOfItems() {

        return totalCostOfItems;
    }

    public void setTotalCostOfItems(BigDecimal totalCostOfItems) {
        this.totalCostOfItems = totalCostOfItems;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="E, MMM dd yyyy HH:mm:ss")
    public LocalDateTime getOrderCreationDate() {
        return orderCreationDate;
    }

    public void setOrderCreationDate(LocalDateTime orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    @Override
    public String toString() {
        return "OrderReport{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", orderStatus=" + orderStatus +
                ", unitPrice=" + unitPrice +
                ", createdDate=" + createdDate +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", totalCostOfItems=" + totalCostOfItems +
                ", productName='" + productName + '\'' +
                ", orderCreationDate=" + orderCreationDate +
                ", orderId=" + orderId +
                '}';
    }
}